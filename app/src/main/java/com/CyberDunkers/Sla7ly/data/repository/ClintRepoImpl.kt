package com.CyberDunkers.Sla7ly.data.repository

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
import com.CyberDunkers.Sla7ly.data.remote.ClintApiInterface
import com.CyberDunkers.Sla7ly.domin.repository.ClintRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ClintRepoImpl @Inject constructor(
    private val clintApiInterface: ClintApiInterface,
) : ClintRepo {

    override suspend fun userRegisteration(phone: String): PhoneRegistrationResponse =
        clintApiInterface.userRegisteration(phone)

    override suspend fun updateUser(userBody: UpdateUserBody): PhoneRegistrationResponse =
        clintApiInterface.userUpdate(userBody)

    override suspend fun userVerify(authVerifyBody: AuthVerifyBody): AuthVerifyResponse =
        clintApiInterface.userVerify(authVerifyBody)

    override suspend fun authWithGoogle(authWithGoogleBody: AuthWithGoogleBody): AuthVerifyResponse =
        clintApiInterface.authWithGoogle(authWithGoogleBody)

    override suspend fun confirmUserData(
        token: RequestBody,
        nationalId: RequestBody,
        imgName : RequestBody,
        image: MultipartBody.Part,
        UserId: RequestBody,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
    ): UserConformation = clintApiInterface.uploadData(
        token, nationalId, imgName ,image ,UserId, address,
        lat, long
    )

    override suspend fun createJob(
        description: RequestBody,
        title: RequestBody,
        userId: RequestBody,
        profession: RequestBody,
        token: RequestBody,
        image: MultipartBody.Part,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
    ): UserConformation = clintApiInterface.createJob(
        description,
        title,
        userId,
        profession,
        token,
        image,
        address,
        lat,
        long
    )

    override suspend fun getUserJobs(token: String, id: String): GetUserJobsResponse =
        clintApiInterface.getUserJobs(token, id)

    override suspend fun getJobComments(token: String, id: String): GetPostCommentsResponse =
        clintApiInterface.getJobComments(token, id)

    override suspend fun selectWorker(selectWorkerBody: SelectWorkerBody): SelectWorkerResponse =
        clintApiInterface.selectWorker(selectWorkerBody)


}