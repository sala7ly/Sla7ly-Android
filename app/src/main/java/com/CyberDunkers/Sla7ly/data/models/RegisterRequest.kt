package com.CyberDunkers.Sla7ly.data.models

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String,
    val passwordConfirm: String,
    val phone: String
)