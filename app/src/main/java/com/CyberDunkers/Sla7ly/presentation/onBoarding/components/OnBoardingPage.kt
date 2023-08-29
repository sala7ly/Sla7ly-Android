package com.CyberDunkers.Sla7ly.presentation.onBoarding.components

import Sla7lyText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.onBoarding.Page
import com.CyberDunkers.Sla7ly.presentation.onBoarding.listOfPages

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    LazyColumn(
        Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = page.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(35.dp))
            Image(painter = painterResource(id = page.img), contentDescription = null)
            Spacer(modifier = Modifier.height(25.dp))

            Sla7lyText(
                text = page.title,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = Constants.MAIN_ORANGE,
                sizeWithSp = 18
            )
            Spacer(modifier = Modifier.height(15.dp))

            Sla7lyText(
                text = page.desc,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                sizeWithSp = 13
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    MaterialTheme {
        OnBoardingPage(page = listOfPages[0])
    }
}