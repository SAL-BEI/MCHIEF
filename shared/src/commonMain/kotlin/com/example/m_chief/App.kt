package com.example.m_chief

import androidx.compose.runtime.*

@Composable
fun App() {
    // Simple state to track if the user is authenticated
    var isAuthenticated by remember { mutableStateOf(false) }

    MChiefTheme {
        if (isAuthenticated) {
            // Show the main dashboard with the bottom navigation
            MainScaffold(
                onLogout = { isAuthenticated = false } // <-- Logs the user out
            )
        } else {
            // Lock the user at the login gate
            LoginScreen(
                onLoginSuccess = { isAuthenticated = true } // <-- Logs the user in
            )
        }
    }
}