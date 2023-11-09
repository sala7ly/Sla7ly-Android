package com.CyberDunkers.Sla7ly.presentation.clint.auth.googleAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse
import com.CyberDunkers.Sla7ly.data.models.AuthWithGoogleBody
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.SaveLoginState
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.AuthWithGoogleUseCase
import com.CyberDunkers.Sla7ly.presentation.clint.auth.OTPAuth.ClintAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClintGoogleAuthViewModel @Inject constructor(
    private val saveLoginState: SaveLoginState,
    private val authWithGoogleUseCase: AuthWithGoogleUseCase
) : ViewModel() {

    //google
    private val _state = mutableStateOf(ClintAuthState())
    val state = _state

    fun authWithGoogle(authWithGoogleBody: AuthWithGoogleBody) {
        authWithGoogleUseCase(authWithGoogleBody).onEach {
            when(it){
                is Resource.Success->{
                    if (it.data?.msg == "created" || it.data?.msg == "login"){
                        _state.value = ClintAuthState(isDataSent = true , data = it.data )
                        saveLoginState(it.data)
                    }
                }
                is Resource.Loading->{
                    _state.value = ClintAuthState(isLoading = true )
                }
                is Resource.Error->{
                    _state.value = ClintAuthState(error = "server error" )
                }
            }
        }.launchIn(viewModelScope)
    }
    fun saveLoginState(response : AuthVerifyResponse) {
        viewModelScope.launch {
            saveLoginState.invoke(response)
        }
    }




}