package com.CyberDunkers.Sla7ly.presentation.clint.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.loginState.LogoutUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClintViewModel @Inject constructor(
    val logoutUseCase: LogoutUseCase ,
    val getTokenUseCase: GetTokenUseCase
) : ViewModel() {


    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
        }
    }

    fun loginData()  {
        viewModelScope.launch {
            getTokenUseCase.getUserId().collect{
                Log.d("userId" , it.toString())

            }
            getTokenUseCase.getToken().collect{
                Log.d("token" , it)
            }

        }
    }
}