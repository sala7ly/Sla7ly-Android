package com.CyberDunkers.Sla7ly.presentation.authentication.worker.login

import BackBtn
import CircularIcon
import LogoPng
import OutLineTextFieldPass
import OutLineTextFieldString
import RoundedBtn
import Sla7lyText
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.navigation.ScreenRoutes
import kotlinx.coroutines.delay

@Composable
fun WorkerLoginScreen(
    navController: NavController,
) {
    val mail = remember {
        mutableStateOf("")
    }
    val pass = remember {
        mutableStateOf("")
    }
    val isVisible = remember {
        mutableStateOf(false)
    }

    val logoWidth by animateIntAsState(
        targetValue = if (!isVisible.value) 262 else 199,
        label = "", animationSpec = tween(800)
    )
    val logoHeight by animateIntAsState(
        targetValue = if (!isVisible.value) 75 else 57,
        label = "", animationSpec = tween(800)
    )
    val weight by animateFloatAsState(
        targetValue = if (!isVisible.value) 1.5f else 1f,
        label = "", animationSpec = tween(800)
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
            .padding(10.dp)

    ) {

        //1
        Column(
            modifier = Modifier
                .weight(weight)
                .fillMaxSize()
        ) {
            BackBtn(onClick = {
                navController.navigate(ScreenRoutes.AuthOptions.route) {
                    popUpTo(ScreenRoutes.AuthOptions.route) {
                        inclusive = true
                    }
                }
            } , modifier = Modifier.padding( start = 5.dp))
            LogoPng(
                contentScale = ContentScale.FillBounds, modifier = Modifier
                    .width(logoWidth.dp)
                    .height(logoHeight.dp)
                    .align(CenterHorizontally)
            )
        }


        //2
        Column(
            Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(2.3f)
                .background(Color.White, shape = RoundedCornerShape(25.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Sla7lyText(
                text = stringResource(R.string.loginn),
                modifier = Modifier.padding(top = 20.dp)
            )
            OutLineTextFieldString(
                { new ->
                    mail.value = new
                },
                stringResource(R.string.enter_your_mail_or_phone_number),
                modifier = Modifier.padding(top = 20.dp)
            )
            OutLineTextFieldPass(
                onNameChange = { enteredText ->
                    pass.value = enteredText
                },
                label = stringResource(R.string.enter_your_password),
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, top = 5.dp), horizontalArrangement = Arrangement.End
            ) {
                Sla7lyText(
                    text = "forgot Password ? ",
                    sizeWithSp = 12,
                    color = Constants.MAIN_ORANGE
                )

            }

            RoundedBtn(
                onClick = {
                    /*TODO*/
                },
                text = stringResource(R.string.login),
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 15.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Sla7lyText(
                    text = stringResource(R.string.i_don_t_have_an_account),
                    sizeWithSp = 12,
                    color = Color.Black
                )
                Sla7lyText(
                    text = " " + stringResource(R.string.new_account),
                    sizeWithSp = 12,
                    color = Constants.MAIN_ORANGE , modifier = Modifier.clickable {
                        navController.navigate(ScreenRoutes.WorkerSignUp.route)
                    }
                )


            }
        }


        //3
        Column(modifier = Modifier.weight(1.3f)) {
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

@Preview
@Composable
fun OrLine() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .weight(3f)
                .background(Constants.MAIN_ORANGE)
        )
        Row(
            modifier = Modifier
                .weight(0.6f)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = CircleShape
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Sla7lyText(
                text = stringResource(R.string.or),
                sizeWithSp = 15, modifier = Modifier, color = Constants.MAIN_ORANGE
            )

        }


        Spacer(
            modifier = Modifier
                .height(2.dp)
                .weight(3f)
                .background(Constants.MAIN_ORANGE)
        )
    }
}




