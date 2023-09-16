package com.CyberDunkers.Sla7ly.domin.usecase.auth

import com.CyberDunkers.Sla7ly.common.Resource
import com.CyberDunkers.Sla7ly.data.models.LoginRequest
import com.CyberDunkers.Sla7ly.data.models.LoginResponse
import com.CyberDunkers.Sla7ly.data.models.RegisterRequest
import com.CyberDunkers.Sla7ly.domin.repository.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    operator fun invoke(registerRequest: RegisterRequest ): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = authRepo.register(registerRequest)
            emit(Resource.Success(response))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred" , null))
        }catch (e : IOException){
            emit(Resource.Error("Couldn't reach server . check your internet connection " , null))
        }
    }
}