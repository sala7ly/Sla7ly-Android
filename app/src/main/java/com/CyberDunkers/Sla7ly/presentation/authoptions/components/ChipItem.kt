package com.CyberDunkers.Sla7ly.presentation.authoptions.components

import Sla7lyText
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.CyberDunkers.Sla7ly.presentation.authoptions.ChipData


@Composable
fun ChipItem(
    chipData: ChipData,
    modifier: Modifier = Modifier,
    textColor : Color = Color.White,
    onClick: () -> Unit,

    ) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight() , horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = modifier
                .clickable {
                    onClick()
                }

        ) {
            Image(
                modifier = Modifier.padding(top = 5.dp),
                painter = painterResource(id = chipData.img),
                contentDescription = null,
                contentScale = ContentScale.Inside

            )
        }

        Sla7lyText(
            text = chipData.jobTitle,
            fontWeight = FontWeight.Bold,
            sizeWithSp = 25,
            textAlign = TextAlign.Center ,
            color = textColor
        )
    }
}

