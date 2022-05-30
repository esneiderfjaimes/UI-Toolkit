package esneiderfjaimes.uitoolkit.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.ln

@Composable
fun SetBarsColor(
    statusColor: Color = MaterialTheme.colorScheme.surface,
    navigationColor: Color = statusColor,
) {
    val systemUiController = rememberSystemUiController()
    val colorElevation = surfaceColorAtElevation(navigationColor, 3.dp)
    SideEffect {
        systemUiController.setStatusBarColor(statusColor)
        systemUiController.setNavigationBarColor(colorElevation)
    }
}

@Composable
fun surfaceColorAtElevation(color: Color, elevation: Dp): Color {
    return if (color == MaterialTheme.colorScheme.surface) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        color
    }
}

fun ColorScheme.surfaceColorAtElevation(
    elevation: Dp,
): Color {
    if (elevation == 0.dp) return surface
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return surfaceTint.copy(alpha = alpha).compositeOver(surface)
}