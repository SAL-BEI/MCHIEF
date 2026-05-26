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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WatuScreen() {
    // State variables to hold the form data
    var fullName by remember { mutableStateOf("") }
    var nationalId by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Module Header
        Text(
            text = "Register Resident",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = KenyaBlack,
            modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
        )

        // The Form Card (Pure White to pop against the Khaki)
        Card(
            colors = CardDefaults.cardColors(containerColor = KenyaWhite),
            shape = RoundedCornerShape(4.dp), // Very slight rounding, boxy and flat
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // No shadow
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Full Name Input
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name *") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = KenyaGreen,
                        unfocusedBorderColor = KenyaBlack,
                        focusedLabelColor = KenyaGreen,
                        cursorColor = KenyaGreen
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // National ID Input (Numeric Keyboard)
                OutlinedTextField(
                    value = nationalId,
                    onValueChange = { nationalId = it },
                    label = { Text("National ID *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = KenyaGreen,
                        unfocusedBorderColor = KenyaBlack,
                        focusedLabelColor = KenyaGreen,
                        cursorColor = KenyaGreen
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Village / Jurisdiction Input
                OutlinedTextField(
                    value = village,
                    onValueChange = { village = it },
                    label = { Text("Village / Household *") },
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

                // Primary Action Button (Kenya Green)
                Button(
                    onClick = {
                        // TODO: Later we will validate data and push to Supabase here
                        println("Saving: $fullName, $nationalId, $village")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = KenyaGreen),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) // Large 56dp touch target for field officers
                ) {
                    Text(
                        text = "SAVE RESIDENT",
                        color = KenyaWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}