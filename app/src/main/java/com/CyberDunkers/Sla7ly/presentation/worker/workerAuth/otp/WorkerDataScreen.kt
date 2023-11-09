package com.CyberDunkers.Sla7ly.presentation.worker.workerAuth.otp

import InfoDialog
import LoadingPage
import OutLineTextFieldString
import RoundedBtn
import Sla7lyText
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
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
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerDataScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.worker.workerAuth.WorkerAuthViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
@Destination
@Composable
fun WorkerDataScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkerAuthViewModel = hiltViewModel(),
    phone: String,
    token: String,
) {
    val firstName = remember {
        mutableStateOf("")
    }
    val infoDialog = remember { mutableStateOf(true) }

    val lastName = remember {
        mutableStateOf("")
    }
    val textColor = remember {
        mutableStateOf(Constants.Blue1)
    }
    val buttonColor = remember {
        mutableStateOf(Color.LightGray)
    }

    val isVisible = remember {
        mutableStateOf(false)
    }
    val state = viewModel.state.value


    if (state.error.isNotEmpty()) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (infoDialog.value) {
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
        navigator.navigate(WorkerHomeScreenDestination){
            popUpTo(WorkerDataScreenDestination.route){
                inclusive = true
            }
        }
    }

    val size by animateFloatAsState(
        targetValue = if (!isVisible.value) 1f else 0.000001f,
        label = "", animationSpec = tween(500)
    )
    handleButtonColor(textColor, buttonColor, firstName, lastName)

    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }


    if (state.isLoading) {
        LoadingPage()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
                    .weight(3f), verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Sla7lyText(
                        text = "Enter your Name ?",
                        sizeWithSp = 20,
                        fontWeight = FontWeight.ExtraBold,
                        color = Constants.Blue2,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    OutLineTextFieldString(
                        onNameChange = { firstName.value = it },
                        label = "First name",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    )


                    OutLineTextFieldString(
                        onNameChange = { lastName.value = it },
                        label = "Last name",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    )

                }
                Column {
                    RoundedBtn(
                        onClick = {
                            if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty()) {
                                viewModel.UpdateUser(phone, firstName.value, lastName.value, token)
                            }

                        }, text = "Continue", modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .height(50.dp), textColor = textColor.value,
                        buttonColor = buttonColor.value
                    )
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(size)
                    .background(Constants.Blue2)
            ) {


            }
        }


    }
}


fun handleButtonColor(
    textColor: MutableState<Color>,
    buttonColor: MutableState<Color>,
    firsName: MutableState<String>,
    lastName: MutableState<String>,
): Boolean {
    return if (firsName.value.isNotEmpty() && lastName.value.isNotEmpty()) {
        textColor.value = Color.White
        buttonColor.value = Constants.Main_Yellow
        true
    } else {
        textColor.value = Constants.Blue1
        buttonColor.value = Color.LightGray
        false
    }
}