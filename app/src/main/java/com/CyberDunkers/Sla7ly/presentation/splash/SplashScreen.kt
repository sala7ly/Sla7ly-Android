package com.CyberDunkers.Sla7ly.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val isVisible = remember {
        mutableStateOf(false)
    }

    val size by animateFloatAsState(
        targetValue = if (!isVisible.value) 0f else 200f,
        label = "", animationSpec = tween(1000)
    )

    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(300)
            isVisible.value = true
        }
    }


     viewModel.navigateToNextScreen(navigator)

    Column(Modifier.fillMaxSize()) {

        //1
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.yellow_circle),
                contentDescription = "image description",
                modifier = Modifier
                    .size(size.dp)
                    .align(alignment = End),
                alignment = TopEnd
            )
        }

        //2
        Column(
            modifier = Modifier
                .weight(3f)
                .background(Color.White),
        ) {


            Spacer(modifier = Modifier.height(80.dp))
            Box {

            }
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { -1000 }, animationSpec = tween(1000)
                ) + fadeIn(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.top_home),
                    contentDescription = "image description",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .width(85.dp)
                        .height(34.dp)
                    ,
                    alignment = Alignment.TopStart
                )
            }


                Image(
                    painter = painterResource(id = R.drawable.home_logo),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds
                )


            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { 1000 }, animationSpec = tween(1000)
                ) + fadeIn(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.bottom_home),
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .width(80.dp)
                        .height(30.dp)
                    ,
                    contentDescription = "image description",
                    alignment = Alignment.BottomStart
                )
            }


            Spacer(modifier = Modifier.height(50.dp))

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { -1000 }, animationSpec = tween(1000)
                ) + fadeIn(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.half_home),
                    modifier = Modifier.size(285.dp),
                    contentDescription = "image description",
                    alignment = Alignment.BottomStart
                )
            }


        }
    }
    }
