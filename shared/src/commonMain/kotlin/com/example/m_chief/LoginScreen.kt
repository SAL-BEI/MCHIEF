package com.example.m_chief

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: (role: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Fetch colors directly from your MChiefTheme palette
    val colors = MaterialTheme.colorScheme

    Scaffold(
        containerColor = colors.background // Uses ChiefKhaki automatically
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Official Institutional Header
            Text(
                text = "M-CHIEF",
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
                fontSize = 32.sp,
                color = colors.onBackground
            )

            Text(
                text = "DIGITAL GOVERNANCE PORTAL",
                style = MaterialTheme.typography.titleMedium,
                color = colors.secondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Authentic 5-Color Kenyan Flag Ribbon (Repositioned as an official letterhead divider)
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Takes up 80% of the width for a clean, centered look
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
            ) {
                Box(modifier = Modifier.weight(2f).fillMaxHeight().background(colors.secondary)) // KenyaBlack
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(colors.surface))   // KenyaWhite
                Box(modifier = Modifier.weight(2f).fillMaxHeight().background(colors.error))     // KenyaRed
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(colors.surface))   // KenyaWhite
                Box(modifier = Modifier.weight(2f).fillMaxHeight().background(colors.primary))   // KenyaGreen
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sign in to access official administration ledgers",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.secondary.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Official Email Address") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.primary,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = colors.primary,
                    cursorColor = colors.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.primary,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = colors.primary,
                    cursorColor = colors.primary
                )
            )

            // Error message handling
            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage!!,
                    color = colors.error, // KenyaRed
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sign In Button
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null

                    loginUser(
                        emailInput = email,
                        passwordInput = password,
                        onSuccess = { detectedRole ->
                            isLoading = false
                            onLoginSuccess(detectedRole)
                        },
                        onError = { message ->
                            isLoading = false
                            errorMessage = message
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !isLoading,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary, // KenyaGreen for main interactive authority
                    contentColor = colors.onPrimary,
                    disabledContainerColor = colors.primary.copy(alpha = 0.5f),
                    disabledContentColor = colors.onPrimary.copy(alpha = 0.5f)
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = colors.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}