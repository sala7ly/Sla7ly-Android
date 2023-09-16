package com.CyberDunkers.Sla7ly.data.repository

import com.CyberDunkers.Sla7ly.data.models.LoginRequest
import com.CyberDunkers.Sla7ly.data.models.LoginResponse
import com.CyberDunkers.Sla7ly.data.models.RegisterRequest
import com.CyberDunkers.Sla7ly.data.remote.ApiInterface
import com.CyberDunkers.Sla7ly.domin.repository.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val apiInterface: ApiInterface
): AuthRepo {
    override suspend fun login(req: LoginRequest): LoginResponse  = apiInterface.clintLogin(req)

    override suspend fun register(req: RegisterRequest): LoginResponse = apiInterface.clintSignUp(req)

}