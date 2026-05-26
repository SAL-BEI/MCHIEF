package com.example.m_chief

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color

@Composable
fun KesiScreen() {
    var residentId by remember { mutableStateOf("") }
    var disputeType by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Module Header
        Text(
            text = "Log New Dispute",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = KenyaBlack,
            modifier = Modifier.padding(bottom = 16.dp, top = 8.dp)
        )

        // The Case Entry Form Card
        Card(
            colors = CardDefaults.cardColors(containerColor = KenyaWhite),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = residentId,
                    onValueChange = { residentId = it },
                    label = { Text("Resident National ID *") },
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

                OutlinedTextField(
                    value = disputeType,
                    onValueChange = { disputeType = it },
                    label = { Text("Dispute Category (e.g., Land, Debt)") },
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
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Case Description *") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = KenyaGreen,
                        unfocusedBorderColor = KenyaBlack,
                        focusedLabelColor = KenyaGreen,
                        cursorColor = KenyaGreen
                    ),
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    maxLines = 4
                )

                Button(
                    onClick = { println("Logging Case: $residentId, $disputeType") },
                    colors = ButtonDefaults.buttonColors(containerColor = KenyaGreen),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text("LOG DISPUTE", color = KenyaWhite, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Active Cases Section
        Text(
            text = "Active Cases (Unresolved)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = KenyaBlack,
            modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
        )

        // A mock list of active cases showing the Red accent rule you requested
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(3) { index ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = KenyaWhite),
                    shape = RoundedCornerShape(4.dp),
                    // We add a subtle Red border to indicate this case is Open/Urgent
                    border = BorderStroke(1.dp, KenyaRed),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
                        // Thick Red Accent Bar on the left
                        Box(modifier = Modifier.width(6.dp).fillMaxHeight().background(KenyaRed))

                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Case #00${index + 1} - Land Boundary",
                                fontWeight = FontWeight.Bold,
                                color = KenyaBlack
                            )
                            Text(
                                text = "Resident ID: 320019${index}2",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "Status: OPEN",
                                fontSize = 12.sp,
                                color = KenyaRed,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}