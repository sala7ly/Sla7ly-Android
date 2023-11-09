package com.CyberDunkers.Sla7ly.presentation.clint.auth.OTPAuth

import BackBtn
import CustomLoading
import InfoDialog
import PinView
import Sla7lyText
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.common.Constants.PIN_VIEW_TYPE_UNDERLINE
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.EnterPhoneScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.UserDataScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun CodeVerificationScreen(
    navigator: DestinationsNavigator,
    viewModel: ClintAuthOtpViewModel = hiltViewModel(),
    phone: String,
) {

    val context = LocalContext.current
    val isVibrating = remember { mutableStateOf(true) }
    val vibrator: Vibrator? = getSystemService(context, Vibrator::class.java)
    val isShaking = remember { mutableStateOf(false) }
    val state = viewModel.state.value
    val isVisible = remember { mutableStateOf(false) }
    val ableToSend = remember { mutableStateOf(true) }
    val lineColor = remember {
        mutableStateOf(Color.Gray)
    }
    var (pinValue, onPinValueChange) = remember {
        mutableStateOf("")
    }
    val infoDialog = remember { mutableStateOf(true) }
    val translationXState by animateFloatAsState(
        targetValue = if (isShaking.value) 15f else 0f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }
    LaunchedEffect(isShaking.value) {

        if (isShaking.value) {
            delay(100)
            isShaking.value = false
        }
    }





    if (pinValue.length == 4 && ableToSend.value) {
        viewModel.verifyUser(pinValue, phone)
        isVibrating.value = false
        ableToSend.value = false

    }

    if (state.data != null) {
        LaunchedEffect(key1 = Unit) {
            delay(200)
            Log.d("state", state.data.msg)
            if (state.data.msg != "false") {
                isVibrating.value = true
                lineColor.value = Color.Green
                state.data.let {
                    if (state.data.msg=="old user"){
                        navigator.navigate(
                            ClintHomeScreenDestination()
                        )
                    }else{
                        navigator.navigate(
                            UserDataScreenDestination(
                                phone , state.data.token
                            )
                        )
                    }

                }
            }

            if (state.data.msg == "false" && !isVibrating.value) {
                lineColor.value = Color.Red
                vibrateApp(vibrator)
                isVibrating.value = true
                isShaking.value = true
                pinValue = ""
                onPinValueChange("")
                ableToSend.value = true


            }

        }


    }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Constants.Blue2)
    ) {


        // 1
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            visible = isVisible.value,
            enter = scaleIn(tween(700)) + fadeIn(tween(1000))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)
                    )
                    .padding(20.dp)
            ) {

                BackBtn(
                    onClick = {
                        navigator.navigate(EnterPhoneScreenDestination) {
                            popUpTo(EnterPhoneScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }, modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(30.dp))


                Sla7lyText(
                    text = "Verify your mobile number",
                    sizeWithSp = 20,
                    fontWeight = FontWeight.ExtraBold,
                    color = Constants.Blue2,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))

                Sla7lyText(
                    text = "You will receive a SMS with a verification pin on ${phone}",
                    sizeWithSp = 15,
                    fontWeight = FontWeight.Normal,
                    color = Constants.Blue2,
                    textAlign = TextAlign.Start

                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = if (isShaking.value) translationXState.dp else 0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    println(isShaking.value)
                    PinView(
                        pinText = pinValue,
                        unfoucsUnderLineColor = lineColor.value,
                        onPinTextChange = onPinValueChange,
                        type = PIN_VIEW_TYPE_UNDERLINE,
                        containerSize = 60.dp
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))

                if (state.error.isNotEmpty()) {
                    if (infoDialog.value) {
                        InfoDialog(title = "error please try again later ",
                            desc = state.error,
                            imgId = R.drawable.baseline_warning_amber_24,
                            onDismiss = {
                                infoDialog.value = false
                            })
                    }
                }
                if (state.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        CustomLoading(
                            circleSize = 12.dp,
                            spaceBetween = 5.dp,
                            travelDistance = 10.dp
                        )

                    }

                }


            }
        }


        // 2

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Constants.Blue2)
        ) {

        }
    }

}

fun vibrateApp(vibrator: Vibrator?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
    } else {
        //deprecated in API 26
        vibrator?.vibrate(300);
    }
}
