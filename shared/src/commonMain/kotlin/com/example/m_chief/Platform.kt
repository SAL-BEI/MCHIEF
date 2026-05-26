package com.example.m_chief

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform