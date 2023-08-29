package com.CyberDunkers.Sla7ly.presentation.authentication.authoptions.components

import Sla7lyText
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CyberDunkers.Sla7ly.presentation.authentication.authoptions.ChipData


@Composable
fun ChipItem(
    chipData: ChipData,
    modifier: Modifier = Modifier,
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
            textAlign = TextAlign.Center
        )
    }
}

