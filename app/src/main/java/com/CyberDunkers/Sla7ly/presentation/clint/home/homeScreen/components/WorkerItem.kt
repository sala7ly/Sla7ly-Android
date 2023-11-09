package com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components

import Sla7lyText
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.CyberDunkers.Sla7ly.common.Constants


@SuppressLint("SuspiciousIndentation")
@Composable
fun WorkerItem(workerItemData: WorkerItemData, onClick:()->Unit) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFFFFF),
            Color(0x5BFFA41B),

            )
    )


        Column(
            modifier = Modifier
                .padding(15.dp)
                .shadow(10.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .width(160.dp)
                .height(160.dp)

            , horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = workerItemData.imgRes),
                contentDescription = "job image",
                modifier = Modifier
                    .padding(5.dp)
                    .weight(3f)
                    .width(70.dp)
                    .height(90.dp),
            )
            Box(Modifier.weight(2f).fillMaxWidth().background(gradientBrush) , contentAlignment = Alignment.Center) {
                Sla7lyText(
                    text = workerItemData.jobTitle,
                    color = Constants.Blue1,
                    sizeWithSp = 18,
                    modifier = Modifier.padding(start = 15.dp, top = 20.dp),
                    fontWeight = FontWeight.ExtraBold ,
                    textAlign = TextAlign.Center
                )
            }


        }

    }
