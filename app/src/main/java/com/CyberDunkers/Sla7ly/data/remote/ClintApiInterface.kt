package com.CyberDunkers.Sla7ly.data.remote

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
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClintApiInterface {

    @FormUrlEncoded
    @POST("/api/user")
    suspend fun userRegisteration(@Field("phone") phone: String): PhoneRegistrationResponse

    @POST("/api/user/verify")
    suspend fun userVerify(@Body authVerifyBody: AuthVerifyBody): AuthVerifyResponse

    @POST("/api/user/update")
    suspend fun userUpdate(@Body updateUserBody: UpdateUserBody): PhoneRegistrationResponse

    @POST("/api/user/google/callback")
    suspend fun authWithGoogle(@Body authWithGoogleBody: AuthWithGoogleBody): AuthVerifyResponse

    @Multipart
    @POST("/api/user/data/add")
    fun uploadData(
        @Part("token") token: RequestBody,
        @Part("ssd") ssd: RequestBody,
        @Part("img_name")imageName : RequestBody ,
        @Part image : MultipartBody.Part,
        @Part("id") id: RequestBody,
        @Part("zone") zone: RequestBody,
        @Part("length") length: RequestBody,
        @Part("width") width: RequestBody,

    ): UserConformation

    @Multipart
    @POST("/api/job/add")
    fun createJob(
        @Part("description") description: RequestBody,
        @Part("title") title: RequestBody,
        @Part("user_id") user_id: RequestBody,
        @Part("profession") profession: RequestBody,
        @Part("token") token: RequestBody,
        @Part("img") img: MultipartBody.Part,
        @Part("zone") zone: RequestBody,
        @Part("length") length: RequestBody,
        @Part("width") width: RequestBody
    ): UserConformation

    @POST("/api/user/jobs")
    fun getUserJobs(
        @Field("token") token: String ,
        @Field("id") id : String
    ) : GetUserJobsResponse

    @POST("/api/job/comments")
    fun getJobComments(
        @Field("token") token: String ,
        @Field("job_id") job_id : String
    ) : GetPostCommentsResponse

    @POST("/api/job/select")
    fun selectWorker(@Body selectWorkerBody: SelectWorkerBody) : SelectWorkerResponse







}