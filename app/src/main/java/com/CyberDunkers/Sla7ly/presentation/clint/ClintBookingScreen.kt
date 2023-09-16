package com.CyberDunkers.Sla7ly.presentation.clint

import Sla7lyText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ClintBookingScreen(){
    Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
        Sla7lyText(text = "setting screen")
    }
}