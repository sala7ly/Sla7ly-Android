package com.CyberDunkers.Sla7ly.presentation.authentication.worker.signup

import BackBtn
import CircularIcon
import LogoPng
import OutLineTextFieldNumber
import OutLineTextFieldPass
import OutLineTextFieldString
import RoundedBtn
import Sla7lyText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.authentication.worker.login.OrLine
import com.CyberDunkers.Sla7ly.presentation.navigation.ScreenRoutes
import kotlinx.coroutines.delay
import okhttp3.internal.notify


@Composable
fun WorkerSignUpScreen(
    navController: NavController,
) {
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val profession = remember {
        mutableStateOf("")
    }
    val isVisible = remember {
        mutableStateOf(false)
    }

    val weight by animateFloatAsState(
        targetValue = if (!isVisible.value) 1.8f else 1f,
        label = "", animationSpec = tween(1500)
    )
   
    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }

    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .background(Constants.SEC_ORANGE)
            .verticalScroll(rememberScrollState())

    ) {

        //1
        Column(
            modifier = Modifier
                .weight(weight)
                .fillMaxSize()
        ) {
            BackBtn(onClick = {
                navController.navigate(ScreenRoutes.WorkerLogin.route) {
                    popUpTo(ScreenRoutes.WorkerLogin.route) {
                        inclusive = true
                    }
                }
            }, modifier = Modifier.padding(start = 5.dp))
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = fadeIn(tween(2000)),
            ) {
                Image(
                    modifier = Modifier
                        .width(178.dp)
                        .height(430.dp),
                    alignment = Alignment.BottomCenter,
                    painter = painterResource(id = R.drawable.worker_vector),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                )
            }
        }


        //2
        Column(
            Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(2.7f)
                .background(Color.White, shape = RoundedCornerShape(25.dp))
                .padding(start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Sla7lyText(
                text = stringResource(R.string.sign_up),
                modifier = Modifier.padding(top = 5.dp).weight(3f) ,
            )

            Row(Modifier.fillMaxWidth()) {
                OutLineTextFieldString(
                    onNameChange = {
                        firstName.value = it
                    }, label = stringResource(R.string.first_name), modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .height(55.dp)
                )


                OutLineTextFieldString(
                    onNameChange = {
                        lastName.value = it
                    },
                    label = stringResource(R.string.last_name),
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp),
                )

            }
            OutLineTextFieldPass(
                onNameChange = {
                    email.value = it
                },
                label = stringResource(R.string.email),
                modifier = Modifier
                    .height(55.dp)
            )
            OutLineTextFieldPass(
                onNameChange = {
                    password.value = it
                },
                label = stringResource(R.string.password),
                modifier = Modifier
                    .height(55.dp)
            )
            OutLineTextFieldPass(
                onNameChange = {
                    confirmPassword.value = it
                },
                label = stringResource(R.string.confirm_password),
                modifier = Modifier
                    .height(55.dp)

            )

            Row(Modifier.fillMaxWidth()) {

                OutLineTextFieldNumber(
                    onNameChange = {
                        phone.value = it
                    }, label = stringResource(R.string.mobile_phone),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp)
                        .height(55.dp)
                )

                OutLineTextFieldString(
                    onNameChange = {
                        profession.value = it
                    },
                    label = stringResource(R.string.profession),
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp)
                )
            }

            RoundedBtn(
                onClick = {
                    /*TODO*/
                },
                text = stringResource(R.string.sign_up),
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 5.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Sla7lyText(
                    text = stringResource(R.string.i_don_t_have_an_account),
                    sizeWithSp = 12,
                    color = Color.Black, modifier = Modifier.clickable {
                        navController.navigate(ScreenRoutes.WorkerLogin.route) {
                            popUpTo(ScreenRoutes.WorkerLogin.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }


        //3
        Column(modifier = Modifier.weight(1.1f)) {
            OrLine()
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularIcon(resID = R.drawable.facebook, onClick = { /*TODO*/ })
                Spacer(modifier = Modifier.width(17.dp))
                CircularIcon(resID = R.drawable.google, onClick = { /*TODO*/ })
            }
        }
    }
}