package com.example.m_chief

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MCHIEF",
    ) {
        App()
    }
}