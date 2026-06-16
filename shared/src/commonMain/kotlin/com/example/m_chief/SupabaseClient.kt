package com.example.m_chief

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

val supabase: SupabaseClient = createSupabaseClient(
    supabaseUrl = "https://wdcrnijrvlemzyybhkmc.supabase.co",
    supabaseKey = "sb_publishable_TxEQrZBVoaMdMjdLLydBrw_MnI7pUIQ"


) {
    install(Postgrest) // <-- Note the lowercase "rest" here
    install(Auth)
}