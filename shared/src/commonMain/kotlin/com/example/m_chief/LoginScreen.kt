package com.example.m_chief

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Using the Chief Khaki background for the entire screen
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ChiefKhaki
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Title
            Text(
                text = "M-CHIEF",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = KenyaBlack,
                letterSpacing = 2.sp
            )

            Text(
                text = "Integrated E-Governance Platform",
                fontSize = 14.sp,
                color = KenyaBlack,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Login Form Card
            Card(
                colors = CardDefaults.cardColors(containerColor = KenyaWhite),
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Official Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = KenyaGreen,
                            unfocusedBorderColor = KenyaBlack,
                            focusedLabelColor = KenyaGreen,
                            cursorColor = KenyaGreen
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = KenyaGreen,
                            unfocusedBorderColor = KenyaBlack,
                            focusedLabelColor = KenyaGreen,
                            cursorColor = KenyaGreen
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            // TODO: Add actual Supabase Auth logic here in the next step
                            onLoginSuccess()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = KenyaGreen),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth().height(56.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = KenyaWhite, modifier = Modifier.size(24.dp))
                        } else {
                            Text("SECURE LOGIN", color = KenyaWhite, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}