package com.example.m_chief

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Household(
    val id: String? = null, // Nullable so Supabase auto-generates the UUID
    @SerialName("head_national_id") val headNationalId: String? = null,
    @SerialName("village_location") val villageLocation: String,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class Resident(
    @SerialName("national_id") val nationalId: String,
    @SerialName("household_id") val householdId: String, // Connects to the Boma
    @SerialName("full_names") val fullNames: String,
    val gender: String,
    @SerialName("phone_number") val phoneNumber: String? = null,
    @SerialName("email_address") val emailAddress: String? = null,
    @SerialName("marital_status") val maritalStatus: String,
    @SerialName("relation_to_head") val relationToHead: String,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class Dependent(
    val id: String? = null, // Nullable so Supabase auto-generates the UUID
    @SerialName("household_id") val householdId: String,
    @SerialName("full_names") val fullNames: String,
    val gender: String,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("school_attending") val schoolAttending: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)