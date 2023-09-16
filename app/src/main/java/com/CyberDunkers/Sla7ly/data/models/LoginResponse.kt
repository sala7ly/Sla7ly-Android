package com.CyberDunkers.Sla7ly.data.models

data class LoginResponse(
    val message: String,
    val payload: Payload,
    val success: Boolean
)