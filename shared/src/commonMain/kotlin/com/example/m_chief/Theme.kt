package com.example.m_chief

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MChiefColorPalette = lightColorScheme(
    primary = KenyaGreen,
    secondary = KenyaBlack,
    background = ChiefKhaki,
    surface = KenyaWhite,
    error = KenyaRed,
    onPrimary = KenyaWhite,
    onBackground = KenyaBlack,
    onSurface = KenyaBlack
)

@Composable
fun MChiefTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MChiefColorPalette,
        content = content
    )
}