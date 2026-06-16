package com.example.m_chief

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

@Composable
fun FieldDataEntryScreen() {
    val colors = MaterialTheme.colorScheme
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Adult (18+)", "Child / Dependent")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Boma Registration",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colors.secondary,
            modifier = Modifier.padding(bottom = 16.dp, top = 8.dp)
        )

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = colors.background,
            contentColor = colors.primary,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = colors.primary
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title, fontWeight = FontWeight.Bold) },
                    selectedContentColor = colors.primary,
                    unselectedContentColor = colors.secondary.copy(alpha = 0.5f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTabIndex) {
            0 -> AdultResidentForm(colors)
            1 -> DependentForm(colors)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdultResidentForm(colors: ColorScheme) {
    var nationalId by remember { mutableStateOf("") }
    var householdId by remember { mutableStateOf("") } // Corrected to Household ID
    var fullName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    var maritalStatusExpanded by remember { mutableStateOf(false) }
    var maritalStatus by remember { mutableStateOf("") }

    var relationshipExpanded by remember { mutableStateOf(false) }
    var relationToHead by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Primary Details", fontWeight = FontWeight.Bold, color = colors.secondary)

            OutlinedTextField(
                value = nationalId,
                onValueChange = { nationalId = it },
                label = { Text("National ID *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Names *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = householdId,
                onValueChange = { householdId = it },
                label = { Text("Boma / Household ID *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text("Demographics & Household", fontWeight = FontWeight.Bold, color = colors.secondary)

            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Gender (M/F) *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            ExposedDropdownMenuBox(
                expanded = relationshipExpanded,
                onExpandedChange = { relationshipExpanded = !relationshipExpanded }
            ) {
                OutlinedTextField(
                    value = relationToHead,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Relationship to Household Head *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = relationshipExpanded) },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = relationshipExpanded,
                    onDismissRequest = { relationshipExpanded = false }
                ) {
                    listOf("Head", "Spouse", "Partner", "Adult Child", "Sibling", "Other").forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = { relationToHead = option; relationshipExpanded = false }
                        )
                    }
                }
            }

            ExposedDropdownMenuBox(
                expanded = maritalStatusExpanded,
                onExpandedChange = { maritalStatusExpanded = !maritalStatusExpanded }
            ) {
                OutlinedTextField(
                    value = maritalStatus,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Marital Status *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = maritalStatusExpanded) },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = maritalStatusExpanded,
                    onDismissRequest = { maritalStatusExpanded = false }
                ) {
                    listOf("Single", "Married", "Cohabiting", "Widowed").forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = { maritalStatus = option; maritalStatusExpanded = false }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (statusMessage.isNotEmpty()) {
                Text(
                    text = statusMessage,
                    color = if (statusMessage.startsWith("Success")) Color(0xFF006600) else Color(0xFFBB0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    scope.launch {
                        isSubmitting = true
                        statusMessage = "Saving to database..."
                        try {
                            // Perfectly matches your correct Database Model!
                            val newResident = Resident(
                                nationalId = nationalId,
                                householdId = householdId,
                                fullNames = fullName,
                                gender = gender,
                                phoneNumber = phoneNumber,
                                emailAddress = null, // Optional
                                maritalStatus = maritalStatus,
                                relationToHead = relationToHead
                            )
                            supabase.from("residents").insert(newResident)

                            statusMessage = "Success: Resident record saved!"

                            // Clear form
                            nationalId = ""
                            householdId = ""
                            fullName = ""
                            gender = ""
                            phoneNumber = ""
                            maritalStatus = ""
                            relationToHead = ""
                        } catch (e: Exception) {
                            statusMessage = "Error: ${e.message}"
                        } finally {
                            isSubmitting = false
                        }
                    }
                },
                enabled = !isSubmitting,
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(color = colors.onPrimary, modifier = Modifier.size(24.dp))
                } else {
                    Text("SAVE ADULT RECORD", color = colors.onPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DependentForm(colors: ColorScheme) {
    var parentHouseholdId by remember { mutableStateOf("") }
    var childFullName by remember { mutableStateOf("") }
    var childGender by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var schoolAttending by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Link to Household", fontWeight = FontWeight.Bold, color = colors.secondary)

            OutlinedTextField(
                value = parentHouseholdId,
                onValueChange = { parentHouseholdId = it },
                label = { Text("Boma / Household ID *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text("Child's Details", fontWeight = FontWeight.Bold, color = colors.secondary)

            OutlinedTextField(
                value = childFullName,
                onValueChange = { childFullName = it },
                label = { Text("Full Names *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = childGender,
                onValueChange = { childGender = it },
                label = { Text("Gender (M/F) *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                label = { Text("Date of Birth (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = schoolAttending,
                onValueChange = { schoolAttending = it },
                label = { Text("School Attending (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (statusMessage.isNotEmpty()) {
                Text(
                    text = statusMessage,
                    color = if (statusMessage.startsWith("Success")) Color(0xFF006600) else Color(0xFFBB0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    scope.launch {
                        isSubmitting = true
                        statusMessage = "Saving to database..."
                        try {
                            // Perfectly matches your correct Database Model!
                            val newDependent = Dependent(
                                householdId = parentHouseholdId,
                                fullNames = childFullName,
                                gender = childGender,
                                dateOfBirth = dateOfBirth,
                                schoolAttending = schoolAttending
                            )
                            supabase.from("dependents").insert(newDependent)

                            statusMessage = "Success: Child record saved!"

                            // Clear form
                            parentHouseholdId = ""
                            childFullName = ""
                            childGender = ""
                            dateOfBirth = ""
                            schoolAttending = ""
                        } catch (e: Exception) {
                            statusMessage = "Error: ${e.message}"
                        } finally {
                            isSubmitting = false
                        }
                    }
                },
                enabled = !isSubmitting,
                colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(color = colors.onPrimary, modifier = Modifier.size(24.dp))
                } else {
                    Text("SAVE CHILD RECORD", color = colors.onPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}