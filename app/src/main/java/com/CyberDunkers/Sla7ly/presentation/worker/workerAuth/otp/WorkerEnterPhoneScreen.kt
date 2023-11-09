package com.CyberDunkers.Sla7ly.presentation.worker.workerAuth.otp

import BackBtn
import InfoDialog
import LoadingPage
import OutLineTextFieldPhone
import RoundedBtn
import Sla7lyText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.common.validation.isValidPhone
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerCodeVerificationScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerLoginScreenDestination
import com.CyberDunkers.Sla7ly.presentation.worker.workerAuth.WorkerAuthViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun WorkerEnterPhoneScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkerAuthViewModel = hiltViewModel(),
) {

    val number = remember {
        mutableStateOf("")
    }
    val textColor = remember {
        mutableStateOf(Constants.Blue1)
    }
    val buttonColor = remember {
        mutableStateOf(Color.LightGray)
    }
    val verifyNow = remember {
        mutableStateOf(handleButtonColor(textColor, buttonColor, phone = number))
    }
    val infoDialog = remember { mutableStateOf(true) }

    val state = viewModel.state.value




    if (state.error.isNotEmpty()) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (infoDialog.value){
                InfoDialog(title = "error please try again letter ",
                    desc = state.error,
                    imgId = R.drawable.baseline_warning_amber_24,
                    onDismiss = {
                        infoDialog.value = false
                    })
            }
        }
    }


    if (state.isDataSent) {
        val phone  : String = number.value
        navigator.navigate(
            WorkerCodeVerificationScreenDestination(
                phone
            )
        )

    }


    handleButtonColor(textColor, buttonColor, phone = number)

    if (state.isLoading) {
        LoadingPage()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Constants.Blue2)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Column {
                BackBtn(
                    onClick = {
                        navigator.navigate(WorkerLoginScreenDestination) {
                            popUpTo(WorkerLoginScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }, modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 15.dp)
                )
                Sla7lyText(
                    text = "Enter your phone number",
                    sizeWithSp = 20,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Start

                )
                Sla7lyText(
                    text = "Enter your phone number to create an account or log in",
                    sizeWithSp = 15,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
                OutLineTextFieldPhone(onNameChange = { number.value = it }, label = "+20 10 19326050")
            }
            Column {
                RoundedBtn(
                    onClick = {
                        if (number.isValidPhone()) {
                            infoDialog.value = true
                            viewModel.phoneRequest(phone = number.value)
                        }

                    }, text = "Continue", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(50.dp), textColor = textColor.value, buttonColor = buttonColor.value
                )

            }



        }
    }
}

fun handleButtonColor(
    textColor: MutableState<Color>,
    buttonColor: MutableState<Color>,
    phone: MutableState<String>,
): Boolean {
    return if (phone.isValidPhone()) {
        textColor.value = Color.White
        buttonColor.value = Constants.Main_Yellow
        true
    } else {
        textColor.value = Constants.Blue1
        buttonColor.value = Color.LightGray
        false
    }
}