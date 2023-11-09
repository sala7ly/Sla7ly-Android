package com.CyberDunkers.Sla7ly.data.repository

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.data.remote.WorkerApiInterface
import com.CyberDunkers.Sla7ly.domin.repository.WorkerRepo
import javax.inject.Inject

class WorkerRepoImp @Inject constructor(
    val workerApiInterface: WorkerApiInterface,
) : WorkerRepo {

    override suspend fun workerRegisteration(phone: String): PhoneRegistrationResponse =
        workerApiInterface.workerRegisteration(phone)

    override suspend fun updateWorker(updateUserBody: UpdateUserBody): PhoneRegistrationResponse =
        workerApiInterface.userUpdate(updateUserBody)


    override suspend fun workerVerify(authVerifyBody: AuthVerifyBody): AuthVerifyResponse =
        workerApiInterface.workerVerify(authVerifyBody)

    override suspend fun authWithGoogle(authWithGoogleBody: AuthWithGoogleBody): AuthVerifyResponse =
        workerApiInterface.authWithGoogle(authWithGoogleBody)

}