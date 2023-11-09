package com.CyberDunkers.Sla7ly.domin.usecase.clint.selectWorker

import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.SelectWorkerBody
import com.CyberDunkers.Sla7ly.data.models.SelectWorkerResponse
import com.CyberDunkers.Sla7ly.domin.repository.ClintRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SelectWorkerUseCase @Inject constructor(
    private val clintRepo: ClintRepo,

    ) {
    operator fun invoke(selectWorkerBody: SelectWorkerBody): Flow<Resource<SelectWorkerResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = clintRepo.selectWorker(selectWorkerBody)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server . check your internet connection ", null))
        }
    }
}