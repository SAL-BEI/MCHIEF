package com.example.m_chief

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaruaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Module Header
        Text(
            text = "Document Approvals",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = KenyaBlack,
            modifier = Modifier.padding(bottom = 16.dp, top = 8.dp)
        )

        // Pending Requests List for the Chief
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(2) { index ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = KenyaWhite),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (index == 0) "Letter of Good Conduct" else "Proof of Residency",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = KenyaBlack
                            )
                            // Red accent to show it requires action
                            Badge(containerColor = KenyaRed) {
                                Text("Pending", color = KenyaWhite, modifier = Modifier.padding(horizontal = 4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Resident ID: 320019${index}2", color = Color.DarkGray, fontSize = 14.sp)
                        Text(text = "Requested By: Field Officer Mutua", color = Color.DarkGray, fontSize = 14.sp)

                        Spacer(modifier = Modifier.height(16.dp))

                        // Action Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Destructive Action (Red Outline)
                            OutlinedButton(
                                onClick = { println("Rejected Request") },
                                border = BorderStroke(1.dp, KenyaRed),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.weight(1f).height(48.dp)
                            ) {
                                Text("REJECT", color = KenyaRed, fontWeight = FontWeight.Bold)
                            }

                            // Primary Action (Kenya Green)
                            Button(
                                onClick = { println("Generating PDF & QR Code...") },
                                colors = ButtonDefaults.buttonColors(containerColor = KenyaGreen),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.weight(2f).height(48.dp)
                            ) {
                                Text("APPROVE & ISSUE", color = KenyaWhite, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}