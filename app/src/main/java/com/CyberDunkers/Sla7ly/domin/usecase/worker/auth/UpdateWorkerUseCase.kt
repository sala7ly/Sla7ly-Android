package com.CyberDunkers.Sla7ly.domin.usecase.worker.auth

import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.PhoneRegistrationResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.domin.repository.WorkerRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateWorkerUseCase @Inject constructor(
    private val workerRepo: WorkerRepo,
) {
    operator fun invoke(updateUserBody: UpdateUserBody): Flow<Resource<PhoneRegistrationResponse>> = flow {

        try {
            emit(Resource.Loading())
            val response = workerRepo.updateWorker(updateUserBody)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server . check your internet connection ", null))
        }
    }

}

