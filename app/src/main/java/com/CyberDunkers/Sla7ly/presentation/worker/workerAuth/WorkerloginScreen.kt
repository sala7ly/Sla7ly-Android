package com.CyberDunkers.Sla7ly.presentation.worker.workerAuth

import BackBtn
import RoundedBtn
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.destinations.AuthOptionsDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerEnterPhoneScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun WorkerLoginScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkerAuthViewModel = hiltViewModel(),
) {


    val isVisible = remember {
        mutableStateOf(false)
    }

    val weight by animateFloatAsState(
        targetValue = if (!isVisible.value) 6f else 8f,
        label = "", animationSpec = tween(1000)
    )

    LaunchedEffect(isVisible.value) {

        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)


    ) {


        //1
        Column(
            modifier = Modifier
                .weight(weight)
                .fillMaxWidth()
        ) {


            BackBtn(onClick = {
                navigator.navigate(AuthOptionsDestination) {
                    popUpTo(AuthOptionsDestination.route) {
                        inclusive = true
                    }
                }
            }, modifier = Modifier.padding(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(2f) , horizontalAlignment = Alignment.CenterHorizontally){

                Image(painter = painterResource(id = R.drawable.workerimg), contentDescription =""  , contentScale = ContentScale.Fit)

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.5f) , horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.home_logo_org), contentDescription =""  , contentScale = ContentScale.Inside , modifier = Modifier.padding(start = 40.dp , end = 40.dp))

            }


        }

        //2
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .weight(3f , true )
                .fillMaxWidth()

                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Constants.Blue2
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            RoundedBtn(
                onClick = {
                    navigator.navigate(WorkerEnterPhoneScreenDestination)
                },
                text = "continue with phone number",
                modifier = Modifier
                    .width(250.dp)
                    .height(60.dp)
                    .padding(top = 15.dp), buttonColor = Constants.Main_Yellow ,
                textColor = Color.White

            )


        }
    }





}


