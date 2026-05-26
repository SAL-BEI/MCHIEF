package com.example.m_chief

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// Define our screens using the official Material Icons
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Watu : Screen("watu", "Watu", Icons.Filled.Person)
    object Kesi : Screen("kesi", "Kesi", Icons.Filled.List)
    object Barua : Screen("barua", "Barua", Icons.Filled.Email)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Watu) }
    val screens = listOf(Screen.Watu, Screen.Kesi, Screen.Barua)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Column {
                // The Authentic 5-Color Kenyan Flag Ribbon
                Row(modifier = Modifier.fillMaxWidth().height(4.dp)) {
                    Box(modifier = Modifier.weight(2f).fillMaxHeight().background(KenyaBlack))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(KenyaWhite)) // Thin white
                    Box(modifier = Modifier.weight(2f).fillMaxHeight().background(KenyaRed))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(KenyaWhite)) // Thin white
                    Box(modifier = Modifier.weight(2f).fillMaxHeight().background(KenyaGreen))
                }

                // Dark Leather Navigation Bar
                NavigationBar(
                    containerColor = ChiefLeatherBrown,
                    contentColor = KenyaWhite
                ) {
                    screens.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                if (screen == Screen.Barua) {
                                    BadgedBox(badge = { Badge(containerColor = KenyaRed) }) {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.title
                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.title
                                    )
                                }
                            },
                            label = { Text(screen.title) },
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            colors = NavigationBarItemDefaults.colors(
                                // When selected, icon and text are White, resting on a Green pill
                                selectedIconColor = KenyaWhite,
                                selectedTextColor = KenyaWhite,
                                indicatorColor = KenyaGreen,

                                // When unselected, they fade to a light gray to contrast with the brown
                                unselectedIconColor = Color.LightGray,
                                unselectedTextColor = Color.LightGray
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (currentScreen) {
                Screen.Watu -> WatuScreen()
                Screen.Kesi -> KesiScreen()
                Screen.Barua -> BaruaScreen()
            }
        }
    }
}