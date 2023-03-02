package com.red.uitoolkit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import esneiderfjaimes.uitoolkit.NavItem
import esneiderfjaimes.uitoolkit.NavScaffold
import esneiderfjaimes.uitoolkit.NavScaffoldOptions
import esneiderfjaimes.uitoolkit.NavigationBar

val navigations = listOf(
    NavItem(
        icon = Icons.Outlined.Home,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        selectedColor = Color(0xFF5B37B7)
    ),
    NavItem(
        icon = Icons.Outlined.FavoriteBorder,
        label = "Likes",
        selectedIcon = Icons.Filled.Favorite,
        selectedColor = Color(0xFFC9379D)
    ),
    NavItem(
        icon = Icons.Outlined.Search,
        label = "Search",
        selectedIcon = Icons.Filled.Search,
        selectedColor = Color(0xFFE6A919)
    ),
    NavItem(
        icon = Icons.Outlined.Person,
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        selectedColor = Color(0xFF1194AA)
    ),
)

@Composable
fun BaseDemo(
    opts: NavScaffoldOptions = NavScaffoldOptions(),
) {
    NavScaffold(
        navigations = navigations,
        opts = opts,
        content = { indexPage ->
            val color = navigations[indexPage].selectedColor
                ?: MaterialTheme.colorScheme.background

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
            )
        }
    )
}

@Preview
@Composable
fun DemoP1() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        NavigationBar(
            navigations,
            opts = NavScaffoldOptions(
                forceExpansion = false,
            ),
        )
        NavigationBar(
            navigations,
            opts = NavScaffoldOptions(
                forceExpansion = true,
            ),
        )
        NavigationBar(
            navigations,
            opts = NavScaffoldOptions(
                forceExpansion = false,
                floatingBottomBar = true,
            ),
        )
        NavigationBar(
            navigations,
            opts = NavScaffoldOptions(
                forceExpansion = true,
                floatingBottomBar = true,
            ),
        )
    }
}

@Preview
@Composable
fun DemoUIToolkitApp() {
    BaseDemo(
        opts = NavScaffoldOptions(
            floatingBottomBar = false,
            forceExpansion = false,
            safeStateWithPager = true
        ),
    )
}

@Preview
@Composable
fun DemoUIToolkitApp2() {
    BaseDemo(
        opts = NavScaffoldOptions(
            floatingBottomBar = true,
            forceExpansion = false,
            safeStateWithPager = true
        ),
    )
}


@Preview
@Composable
fun DemoUIToolkitApp3() {
    BaseDemo(
        opts = NavScaffoldOptions(
            floatingBottomBar = true,
            forceExpansion = true,
            safeStateWithPager = true
        ),
    )
}

@Preview
@Composable
fun DemoUIToolkitApp4() {
    BaseDemo(
        opts = NavScaffoldOptions(
            floatingBottomBar = false,
            forceExpansion = true,
            safeStateWithPager = true
        ),
    )
}