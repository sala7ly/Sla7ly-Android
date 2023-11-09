package com.CyberDunkers.Sla7ly.presentation.clint.auth.googleAuth

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)