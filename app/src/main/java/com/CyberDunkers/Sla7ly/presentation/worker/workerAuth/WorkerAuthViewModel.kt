package com.CyberDunkers.Sla7ly.presentation.worker.workerAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.LoginStateUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.WorkerRegistrationUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.WorkerVerifyUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.worker.auth.UpdateWorkerUseCase
import com.CyberDunkers.Sla7ly.presentation.clint.auth.OTPAuth.ClintAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkerAuthViewModel @Inject constructor(
    private val workerRegistrationUseCase: WorkerRegistrationUseCase,
    private val workerVerifyUseCase: WorkerVerifyUseCase,
    private val updateWorkerUseCase: UpdateWorkerUseCase,
   private val loginStateUseCases: LoginStateUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ClintAuthState())
    val state = _state

    private val _phone = MutableLiveData<String>()
    val phone: MutableLiveData<String> = _phone

    init {
        println("from init block ${_phone.value}")
    }

    fun phoneRequest(phone: String) {
        workerRegistrationUseCase(phone).onEach {
            when (it) {
                is Resource.Success -> {
                    state.value = ClintAuthState(isDataSent = true)
                    _phone.value = phone
                }

                is Resource.Loading -> {
                    state.value = ClintAuthState(isLoading = true)
                }

                is Resource.Error -> {
                    state.value = ClintAuthState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun verifyUser(codeVerification: String, phone: String) {
        val authVerifyBody = AuthVerifyBody(phone, codeVerification)
        workerVerifyUseCase(authVerifyBody).onEach {
            when (it) {

                is Resource.Success -> {

                    state.value = ClintAuthState(data = it.data)
                    saveUserLogin(response = it.data ?: AuthVerifyResponse("" , "", -1))

                }

                is Resource.Loading -> {
                    state.value = ClintAuthState(isLoading = true)
                }

                is Resource.Error -> {
                    state.value = ClintAuthState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)


    }

    fun UpdateUser(phone: String, firstName: String, lastName: String, token: String) {
        val userBody = UpdateUserBody(
            phone = phone,
            first_name = firstName,
            last_name = lastName,
            token = token
        )
        updateWorkerUseCase(userBody).onEach {

            println(userBody)

            when (it) {
                is Resource.Success -> {
                    state.value = ClintAuthState(isDataSent = true)
                }

                is Resource.Loading -> {
                    state.value = ClintAuthState(isLoading = true)
                }

                is Resource.Error -> {
                    state.value = ClintAuthState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)


    }

    fun saveUserLogin(response : AuthVerifyResponse) {
        viewModelScope.launch {
          loginStateUseCases.saveLoginState.invoke(response)
        }
    }


}