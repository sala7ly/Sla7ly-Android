package com.CyberDunkers.Sla7ly.domin.repository

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.data.models.GetPostCommentsResponse
import com.CyberDunkers.Sla7ly.data.models.GetUserJobsResponse
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.SelectWorkerBody
import com.CyberDunkers.Sla7ly.data.models.SelectWorkerResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.data.models.UserConformation
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ClintRepo {

    suspend fun userRegisteration(phone: String): PhoneRegistrationResponse
    suspend fun userVerify(authVerifyBody: AuthVerifyBody): AuthVerifyResponse
    suspend fun updateUser(updateUserBody: UpdateUserBody): PhoneRegistrationResponse
    suspend fun authWithGoogle(authWithGoogleBody: AuthWithGoogleBody): AuthVerifyResponse

    suspend fun confirmUserData(
        token: RequestBody,
        nationalId: RequestBody,
        imgName : RequestBody,
        image: MultipartBody.Part,
        UserId: RequestBody,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
    ): UserConformation

    suspend fun createJob(
        description: RequestBody,
        title: RequestBody,
        userId: RequestBody,
        profession: RequestBody,
        token: RequestBody,
        image: MultipartBody.Part,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
    ): UserConformation

    suspend fun getUserJobs(
        token: String,
        id: String,
    ): GetUserJobsResponse

    suspend fun getJobComments(
        token: String,
        id: String,
    ): GetPostCommentsResponse

    suspend fun selectWorker(
        selectWorkerBody: SelectWorkerBody,
    ): SelectWorkerResponse


}