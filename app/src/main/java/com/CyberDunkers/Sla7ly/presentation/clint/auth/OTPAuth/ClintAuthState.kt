package com.CyberDunkers.Sla7ly.presentation.clint.auth.OTPAuth

import com.CyberDunkers.Sla7ly.data.models.AuthVerifyResponse

data class ClintAuthState(
    val isDataSent : Boolean = false  ,
    val phone : String = "",
    val data : AuthVerifyResponse ?= null  ,
    val isLoading : Boolean = false ,
    val error : String = ""

)
