package com.CyberDunkers.Sla7ly.domin.repository

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody

interface WorkerRepo {
    suspend fun workerRegisteration(phone : String) : PhoneRegistrationResponse
    suspend fun updateWorker(updateUserBody: UpdateUserBody) : PhoneRegistrationResponse
    suspend fun workerVerify(authVerifyBody: AuthVerifyBody) : AuthVerifyResponse
    suspend fun authWithGoogle(authWithGoogleBody: AuthWithGoogleBody) : AuthVerifyResponse

}