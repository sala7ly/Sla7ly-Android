package com.CyberDunkers.Sla7ly.presentation.authentication.clint

import com.CyberDunkers.Sla7ly.data.models.LoginResponse
import com.CyberDunkers.Sla7ly.data.models.Payload

data class ClintAuthState(
    val data : LoginResponse ?= null  ,
    val isLoading : Boolean = false ,
    val error : String = "" ,
    val success :Boolean = false

)
