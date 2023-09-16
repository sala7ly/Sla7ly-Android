package com.CyberDunkers.Sla7ly.presentation.authentication.clint

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

data class EditeTextLabels(
    val mailLabel : MutableState<String> = mutableStateOf("enter you email"),
    val passLabel : MutableState<String> = mutableStateOf("enter your pass"),
    var mailColor : Color = Color.Gray,
    var passColor : Color = Color.Gray,

    )
