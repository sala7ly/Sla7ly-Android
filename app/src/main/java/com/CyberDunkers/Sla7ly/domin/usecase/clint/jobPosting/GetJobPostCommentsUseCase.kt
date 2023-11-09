package com.CyberDunkers.Sla7ly.domin.usecase.clint.jobPosting

import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.GetPostCommentsResponse
import com.CyberDunkers.Sla7ly.domin.repository.ClintRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetJobPostCommentsUseCase @Inject constructor(
    private val clintRepo: ClintRepo,

    ) {

    operator fun invoke(token :String , id : String): Flow<Resource<GetPostCommentsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = clintRepo.getJobComments(token , id )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "un excepted error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server . check your internet connection ", null))
        }
    }
}