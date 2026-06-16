package com.example.m_chief

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String,
    val role: String
)