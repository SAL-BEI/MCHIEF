package com.example.m_chief

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.PostgREST

// This creates a globally accessible client to query your database and authenticate users.
val supabase = createSupabaseClient(
    supabaseUrl = "YOUR_SUPABASE_PROJECT_URL",
    supabaseKey = "YOUR_SUPABASE_ANON_KEY"
) {
    install(Auth)
    install(PostgREST)
}