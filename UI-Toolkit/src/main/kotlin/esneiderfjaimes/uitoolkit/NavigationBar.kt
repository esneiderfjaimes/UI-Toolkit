package esneiderfjaimes.uitoolkit

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = 3.0.dp,
    content: @Composable RowScope.() -> Unit,
) {
    BaseNavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation, content = content
    )
}

@Composable
fun FloatingNavigationBar(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = 3.0.dp,
    content: @Composable RowScope.() -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BaseNavigationBar(
            modifier = modifier.padding(paddingValues),
            containerColor = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            shape = CircleShape,
            content = content
        )
    }
}

@Composable
private fun BaseNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = 3.0.dp,
    shape: Shape = Shapes.None,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier,
        shape = shape
    ) {
        Row(
            modifier = Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
fun NavBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
    ),
) {
    val iconColor by colors.iconColor(selected = selected)
    val styledIcon = @Composable {
        CompositionLocalProvider(LocalContentColor provides iconColor, content = icon)
    }

    val styledLabel: @Composable (() -> Unit)? = label?.let {
        @Composable {
            val style = MaterialTheme.typography.labelMedium
            CompositionLocalProvider(LocalContentColor provides iconColor) {
                ProvideTextStyle(style, content = label)
            }
        }
    }

    val clickAndSemanticsModifier =
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
            enabled = enabled,
            onClickLabel = null,
            role = Role.Button,
            onClick = onClick
        )

    Box(modifier = modifier
        .clip(CircleShape)
        .then(clickAndSemanticsModifier),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(if (selected) colors.indicatorColor.copy(0.125f) else Color.Transparent)
            .padding(16.dp, 8.dp)
            .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            styledIcon()
            if (selected && styledLabel != null) {
                styledLabel()
            }
        }
    }
}

data class NavItem(
    val icon: ImageVector,
    val label: String? = null,
    val selectedIcon: ImageVector = icon,
    val selectedColor: Color? = null,
)
