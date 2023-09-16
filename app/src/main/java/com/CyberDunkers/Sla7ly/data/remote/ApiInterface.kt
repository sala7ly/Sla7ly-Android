package com.CyberDunkers.Sla7ly.data.remote

import com.CyberDunkers.Sla7ly.data.models.LoginRequest
import com.CyberDunkers.Sla7ly.data.models.LoginResponse
import com.CyberDunkers.Sla7ly.data.models.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

@POST("/api/v1/auth/login")
suspend fun clintLogin(@Body request: LoginRequest) : LoginResponse

@POST("/api/v1/auth/register")
suspend fun clintSignUp(@Body request: RegisterRequest) : LoginResponse


}