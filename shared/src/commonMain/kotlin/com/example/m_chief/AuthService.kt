package com.example.m_chief

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun loginUser(
    emailInput: String,
    passwordInput: String,
    onSuccess: (role: String) -> Unit,
    onError: (message: String) -> Unit
) {
    CoroutineScope(Dispatchers.Default).launch {
        try {
            // 1. Log in via Supabase Auth
            supabase.auth.signInWith(Email) {
                email = emailInput
                password = passwordInput
            }

            // 2. Get current user session
            val currentUser = supabase.auth.currentUserOrNull()
            if (currentUser != null) {

                // 3. Fetch role from 'profiles' table
                val profile = supabase.postgrest["profiles"]
                    .select {
                        filter {
                            eq("id", currentUser.id)
                        }
                    }.decodeSingle<UserProfile>()

                onSuccess(profile.role)
            } else {
                onError("Failed to retrieve user data.")
            }

        } catch (e: Exception) {
            onError(e.message ?: "An unknown error occurred during login")
        }
    }
}