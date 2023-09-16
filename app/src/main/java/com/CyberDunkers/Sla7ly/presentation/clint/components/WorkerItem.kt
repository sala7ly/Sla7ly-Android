package com.CyberDunkers.Sla7ly.presentation.clint.components

import Sla7lyText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.CyberDunkers.Sla7ly.common.Constants



@Composable
fun WorkerItem(workerItemData: WorkerItemData , onClick:()->Unit) {

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF173D3D),
            Color(0xFF334242),
            Color(0xFF454545),

        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(10.dp)
    ) {


        Column(
            modifier = Modifier
                .height(120.dp)
                .background(brush = gradientBrush, shape = RoundedCornerShape(15.dp)).clickable {
                    onClick()
                }
        ) {

            Sla7lyText(
                text = workerItemData.jobTitle,
                color = Constants.SEC_ORANGE,
                sizeWithSp = 20,
                modifier = Modifier.padding(start = 15.dp , top = 20.dp) ,
                fontWeight = FontWeight.ExtraBold
            )
            Sla7lyText(
                text = workerItemData.desc,
                color = Color.White,
                sizeWithSp = 13,
                modifier = Modifier.padding(end = 170.dp, start = 10.dp)
            )

        }

        Image(
            painter = painterResource(id = workerItemData.imgRes),
            contentDescription = "job image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.align(
                Alignment.CenterEnd
            )
        )

    }
}