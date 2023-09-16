package com.CyberDunkers.Sla7ly.presentation.authentication.clint

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.common.Resource
import com.CyberDunkers.Sla7ly.data.models.LoginRequest
import com.CyberDunkers.Sla7ly.data.models.RegisterRequest
import com.CyberDunkers.Sla7ly.domin.usecase.auth.RegisterUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.auth.LoginUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.SaveLoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClintAuthViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    private val saveLoginState: SaveLoginState,
) : ViewModel() {

    private val _state = mutableStateOf(ClintAuthState())
    val state = _state

    fun login(loginRequest: LoginRequest) {
        loginUseCase(loginRequest).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = ClintAuthState(data = it.data, success = true)
                }

                is Resource.Error -> {
                    _state.value = ClintAuthState(error = it.message.toString())
                }

                is Resource.Loading -> {
                    _state.value = ClintAuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun register(registerRequest: RegisterRequest) {
        registerUseCase(registerRequest).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = ClintAuthState(data = it.data, success = true)
                }

                is Resource.Error -> {
                    _state.value = ClintAuthState(error = it.message.toString())
                }

                is Resource.Loading -> {
                    _state.value = ClintAuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun saveLoginState() {
        viewModelScope.launch {
            saveLoginState.invoke()
        }
    }

}