package com.example.m_chief

import androidx.compose.runtime.*

@Composable
fun App() {
    // State to track the authenticated user's role (null means not logged in)
    var loggedInRole by remember { mutableStateOf<String?>(null) }

    MChiefTheme {
        if (loggedInRole != null) {
            // Show the main dashboard with the bottom navigation, passing the specific role
            MainScaffold(
                userRole = loggedInRole!!,
                onLogout = { loggedInRole = null } // <-- Logs the user out by clearing the role
            )
        } else {
            // Lock the user at the login gate
            LoginScreen(
                onLoginSuccess = { role ->
                    loggedInRole = role
                } // <-- Logs the user in and saves their role
            )
        }
    }
}