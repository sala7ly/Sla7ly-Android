package com.CyberDunkers.Sla7ly.domin.usecase.clint.DataConfermation

import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.UserConformation
import com.CyberDunkers.Sla7ly.domin.repository.ClintRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataConformationUseCase @Inject constructor(
    private val clintRepo: ClintRepo,
) {
    operator fun invoke(
        token: RequestBody,
        nationalId: RequestBody,
        imgName : RequestBody,
        image : MultipartBody.Part,
        UserId: RequestBody,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
   ): Flow<Resource<UserConformation>> = flow {
        try {
            emit(Resource.Loading())
            val response = clintRepo.confirmUserData(
                token,
                nationalId,
                imgName ,
                image,
                UserId,
                address,
                lat,
                long,
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server . check your internet connection ", null))
        }
    }

}