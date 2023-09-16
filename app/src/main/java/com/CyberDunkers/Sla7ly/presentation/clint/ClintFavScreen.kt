package com.CyberDunkers.Sla7ly.presentation.clint

import Sla7lyText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.CyberDunkers.Sla7ly.common.Constants
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ClintFavScreen(){
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.weight(1f).background(Color.White).fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Sla7lyText(text = "Favorite ")
        }
        Column(modifier = Modifier.weight(4f).background(Constants.THIRD_Orange , RoundedCornerShape(topStart = 100.dp , topEnd = 100.dp)).fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Sla7lyText(text = "Empty" , color = Color.White)
        }
    }
}