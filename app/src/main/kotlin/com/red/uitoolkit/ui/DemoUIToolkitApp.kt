package com.red.uitoolkit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import esneiderfjaimes.uitoolkit.NavItem
import esneiderfjaimes.uitoolkit.NavScaffold
import esneiderfjaimes.uitoolkit.NavScaffoldOptions

val navigations = listOf(
    NavItem(icon = Icons.Outlined.Home,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        selectedColor = Color(0xFF5B37B7)),
    NavItem(icon = Icons.Outlined.FavoriteBorder,
        label = "Likes",
        selectedIcon = Icons.Filled.Favorite,
        selectedColor = Color(0xFFC9379D)),
    NavItem(icon = Icons.Outlined.Search,
        label = "Search",
        selectedIcon = Icons.Filled.Search,
        selectedColor = Color(0xFFE6A919)),
    NavItem(icon = Icons.Outlined.Person,
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        selectedColor = Color(0xFF1194AA)),
)

@Composable
fun DemoUIToolkitApp() {
    NavScaffold(
        navigations = navigations,
        opts = NavScaffoldOptions(
            floatingBottomBar = false,
            forceExpansion = false,
            safeStateWithPager = true
        ),
        content = { indexPage ->
            val color = navigations[indexPage].selectedColor
                ?: MaterialTheme.colorScheme.background

            Box(Modifier
                .fillMaxSize()
                .background(color))
        }
    )
}