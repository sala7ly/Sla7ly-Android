package com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.common.toRequestBody
import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.GetTokenUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.DataConfermation.DataConformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val dataConformationUseCase: DataConformationUseCase,
    val getTokenUseCase: GetTokenUseCase,

    ) : ViewModel() {

    private val token = mutableStateOf("")
    private val id = mutableStateOf("")

    init {
        getId()
        getToken()
        Log.d("token", token.value)
        Log.d("id", id.value)
    }

    fun dataConformation(
        nationalId: RequestBody,
        address: RequestBody,
        lat: RequestBody,
        long: RequestBody,
        image: MultipartBody.Part,
    ) {


        dataConformationUseCase.invoke(
            token.value.toRequestBody(),
            nationalId,
            "profilePic".toRequestBody(),
            image,
            id.value.toRequestBody(),
            address,
            lat,
            long
        )
            .onEach {

                when (it) {
                    is Resource.Success -> {

                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }
    }

    private fun getId() {
        viewModelScope.launch {
            getTokenUseCase.getToken().collect {
                token.value = it
            }
        }
    }

    private fun getToken() {
        viewModelScope.launch {

            getTokenUseCase.getUserId().collect {
                id.value = it.toString()
            }
        }
    }

}