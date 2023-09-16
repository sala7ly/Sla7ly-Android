package com.CyberDunkers.Sla7ly.presentation.clint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClintViewModel @Inject constructor(
    val logoutUseCase: LogoutUseCase
) : ViewModel() {


    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
        }
    }
}