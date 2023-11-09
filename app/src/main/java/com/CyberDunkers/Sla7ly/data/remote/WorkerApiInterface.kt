package com.CyberDunkers.Sla7ly.data.remote

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.data.models.GetAllJobsResponse
import com.CyberDunkers.Sla7ly.data.models.GetSpecificJobBody
import com.CyberDunkers.Sla7ly.data.models.GetSpecificJobResponse
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkerApiInterface {

    @FormUrlEncoded
    @POST("/api/worker")
    suspend fun workerRegisteration(@Field("phone") phoneNumber: String): PhoneRegistrationResponse

    @POST("/api/worker/verify")
    suspend fun workerVerify(@Body authVerifyBody: AuthVerifyBody): AuthVerifyResponse

    @POST("/api/worker/update")
    suspend fun userUpdate(@Body updateUserBody: UpdateUserBody): PhoneRegistrationResponse

    @GET("/api/user/google/callback}")
    suspend fun authWithGoogle(@Body authWithGoogleBody: AuthWithGoogleBody): AuthVerifyResponse

    @POST("/api/jobs")
    suspend fun getAllJobs(@Field("token") token: String): GetAllJobsResponse

    @POST("/api/jobs/some")
    suspend fun getSpecificJob(@Body getSpecificJobBody: GetSpecificJobBody): GetSpecificJobResponse


}