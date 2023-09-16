package com.CyberDunkers.Sla7ly.domin.repository

import com.CyberDunkers.Sla7ly.data.models.LoginRequest
import com.CyberDunkers.Sla7ly.data.models.LoginResponse
import com.CyberDunkers.Sla7ly.data.models.RegisterRequest

interface AuthRepo {

    suspend fun login(req : LoginRequest) :LoginResponse

    suspend fun register(req : RegisterRequest) : LoginResponse

}