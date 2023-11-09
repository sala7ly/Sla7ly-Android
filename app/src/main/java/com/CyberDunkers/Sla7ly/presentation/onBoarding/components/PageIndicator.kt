package com.CyberDunkers.Sla7ly.presentation.onBoarding.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.CyberDunkers.Sla7ly.common.Constants

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: androidx.compose.ui.graphics.Color = Constants.Main_Yellow,
    unSelectedColor: androidx.compose.ui.graphics.Color = Constants.Blue3,
) {

    Row(modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { page ->
            val width = animateDpAsState(
                targetValue = if (page == selectedPage) 30.dp else 10.dp, animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                ),
                label = ""
            )
            

            Box(
                modifier = Modifier
                    .width(width.value)
                    .height(10.dp)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unSelectedColor)
            )
            Spacer(modifier = Modifier.width(5.dp))

        }

    }
}