package dev.lesam.androidconferences.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider

private val DarkColorPalette = darkColors(
        primary = purple200,
        primaryVariant = purple700,
        secondary = teal200
)

private val LightColorPalette = lightColors(
        primary = purple500,
        primaryVariant = purple700,
        secondary = teal200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AndroidConferencesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
    )
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), composableComponent: @Composable () -> Unit) {
    AndroidConferencesTheme(darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            composableComponent()
        }
    }
}

class ThemesProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}

@Composable
fun AppThemedPreview(
    @PreviewParameter(ThemesProvider::class) isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    AppTheme(isDarkTheme) {
        content()
    }
}