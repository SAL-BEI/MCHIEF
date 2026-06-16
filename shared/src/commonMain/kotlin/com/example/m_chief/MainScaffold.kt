package com.example.m_chief

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// SEALED CLASS
// Expanded sealed class to include both Chief and Field Officer screens
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    // Chief Screens
    object Watu : Screen("watu", "Watu", Icons.Filled.Person)
    object Kesi : Screen("kesi", "Kesi", Icons.Filled.List)
    object Barua : Screen("barua", "Barua", Icons.Filled.Email)

    // Field Officer Screens
    object DataEntry : Screen("data_entry", "Data Entry", Icons.Filled.Edit)
    object SyncStatus : Screen("sync_status", "Sync Status", Icons.Filled.CloudSync)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    userRole: String,
    onLogout: () -> Unit
) {
    // Determine available screens based on user role
    val screens = if (userRole.lowercase() == "chief") {
        listOf(Screen.Watu, Screen.Kesi, Screen.Barua)
    } else {
        listOf(Screen.DataEntry, Screen.SyncStatus)
    }

    // Default to the first screen in the user's allowed list
    var currentScreen by remember { mutableStateOf<Screen>(screens.first()) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        // Restored bold M-CHIEF styling
                        Text("M-CHIEF", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
                        // Dynamic role subtitle
                        Text(
                            text = if (userRole.lowercase() == "chief") "Role: Administrator (Chief)" else "Role: Field Operations Officer",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ChiefKhakiDark,
                    titleContentColor = KenyaBlack,
                    actionIconContentColor = KenyaBlack
                ),
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Secure Logout"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Column {
                // The Authentic 5-Color Kenyan Flag Ribbon
                Row(modifier = Modifier.fillMaxWidth().height(4.dp)) {
                    Box(modifier = Modifier.weight(2f).fillMaxHeight().background(KenyaBlack))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(KenyaWhite))
                    Box(modifier = Modifier.weight(2f).fillMaxHeight().background(KenyaRed))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(KenyaWhite))
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
                                // Badge logic for Barua
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
                                selectedIconColor = KenyaWhite,
                                selectedTextColor = KenyaWhite,
                                indicatorColor = KenyaGreen, // Green Pill
                                unselectedIconColor = Color.LightGray,
                                unselectedTextColor = Color.LightGray
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Route to the isolated standalone view files
            when (currentScreen) {
                Screen.Watu -> ChiefWatuScreen()
                Screen.Kesi -> KesiScreen()
                Screen.Barua -> BaruaScreen()
                Screen.DataEntry -> FieldDataEntryScreen()
                Screen.SyncStatus -> FieldSyncScreen()
            }
        }
    }
}