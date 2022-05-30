@file:OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)

package esneiderfjaimes.uitoolkit

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import esneiderfjaimes.uitoolkit.NavBarItem as RedNavBarItem
import esneiderfjaimes.uitoolkit.utils.ClickPage
import esneiderfjaimes.uitoolkit.utils.SetBarsColor
import kotlinx.coroutines.launch

@Composable
fun NavScaffold(
    navigations: List<NavItem>,
    opts: NavScaffoldOptions = NavScaffoldOptions(),
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable (Int) -> Unit,
) {
    RedScaffoldBase(navigations = navigations, opts, topBar, content)
}

data class NavScaffoldOptions(
    val safeStateWithPager: Boolean = false,
    val autoSetBarsColor: Boolean = true,
    val floatingBottomBar: Boolean = false,
    val forceExpansion: Boolean = false,
    val labelVisibility: LabelVisibility = LabelVisibility.LABELED
)

enum class LabelVisibility { SELECTED, LABELED, UNLABELED }

@Composable
private fun RedScaffoldBase(
    navigations: List<NavItem>,
    opts: NavScaffoldOptions = NavScaffoldOptions(),
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable (Int) -> Unit,
) {
    if (navigations.isEmpty()) return

    if (opts.autoSetBarsColor)
        SetBarsColor()

    val topBarContent: @Composable
        (Int) -> Unit = { indexSelected ->
        if (topBar != null) topBar()
        else DefaultTopBar(label = navigations[indexSelected].label)
    }

    val bottomBar: @Composable (Int, ClickPage) -> Unit = { indexSelected, onClick ->
        if (opts.floatingBottomBar) {
            FloatingNavigationBar {
                BaseBottomBar(navigations,
                    indexSelected = indexSelected,
                    opts.forceExpansion, onClick = onClick)
            }
        } else {
            NavigationBar {
                BaseBottomBar(navigations,
                    indexSelected = indexSelected,
                    opts.forceExpansion, onClick = onClick)
            }
        }
    }

    if (opts.safeStateWithPager) {
        val pagerState: PagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { topBarContent(pagerState.currentPage) },
            content = { _ ->
                HorizontalPager(count = navigations.size,
                    state = pagerState) { page -> content(page) }
            },
            bottomBar = {
                bottomBar(pagerState.currentPage) { scope.launch { pagerState.scrollToPage(it) } }
            }
        )
    } else {
        var indexSelected by remember { mutableStateOf(0) }

        Scaffold(
            topBar = { topBarContent(indexSelected) },
            content = { _ -> content(indexSelected) },
            bottomBar = { bottomBar(indexSelected) { indexSelected = it } }
        )
    }
}

@Composable
private fun RowScope.BaseBottomBar(
    items: List<NavItem>,
    indexSelected: Int,
    forceExpansion: Boolean = false,
    onClick: (Int) -> Unit,
) {
    items.forEachIndexed { index, item ->
        val isSelected = indexSelected == index

        // TODO: Fix label in force expansion
        val label: @Composable (() -> Unit)? =
            if (forceExpansion) null else item.label?.let { { Text(it, maxLines = 1) } }

        val colors: NavigationBarItemColors =
            (item.selectedColor
                ?: MaterialTheme.colorScheme.primary).let { selectedColor ->
                NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedColor,
                    selectedTextColor = selectedColor,
                    indicatorColor = selectedColor,
                )
            }

        RedNavBarItem(
            selected = isSelected,
            onClick = { onClick(index) },
            icon = {
                Icon(if (isSelected) item.selectedIcon else item.icon,
                    contentDescription = null)
            },
            modifier = if (forceExpansion) Modifier.weight(1f) else Modifier,
            label = label,
            colors = colors
        )
    }
}

@Composable
private fun DefaultTopBar(label: String?) {
    CenterAlignedTopAppBar(
        title = {
            label?.let {
                Text(text = it,
                    modifier = Modifier.animateContentSize(),
                    maxLines = 1)
            }
        }
    )
}