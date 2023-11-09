package com.CyberDunkers.Sla7ly.domin.usecase.clint.auth

import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.domin.repository.WorkerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WorkerVerifyUseCase  @Inject constructor(
    private val workerRepo: WorkerRepo,
) {
    operator fun invoke(authVerifyBody: AuthVerifyBody): Flow<Resource<AuthVerifyResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = workerRepo.workerVerify(authVerifyBody)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server . check your internet connection ", null))
        }
    }

}

