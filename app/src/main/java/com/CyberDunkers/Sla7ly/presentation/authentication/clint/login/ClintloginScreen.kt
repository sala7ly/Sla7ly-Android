package com.CyberDunkers.Sla7ly.presentation.authentication.clint.login

import OutLineTextFieldPass
import OutLineTextFieldString
import RoundedBtn
import Sla7lyText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants

@Composable
fun ClintLoginScreen(
    navController: NavController
){
    val mail = remember {
        mutableStateOf("")
    }
    val pass = remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Constants.SEC_ORANGE)) {
        Column(
            Modifier
                .padding(top = 90.dp, start = 8.dp, end = 8.dp)
                .width(366.dp)
                .height(434.dp)
                .background(Color.White, shape = RoundedCornerShape(25.dp)) , horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center) {



            Sla7lyText(text = "login")
            OutLineTextFieldString( { new ->
                mail.value = new
            }, stringResource(R.string.enter_your_mail_or_phone_number))
            OutLineTextFieldPass(onNameChange = { enteredText->
                pass.value = enteredText
            }, label = stringResource(R.string.enter_your_password) )
            RoundedBtn(onClick = { /*TODO*/ }, text = stringResource(R.string.login) )
        }
    }
}

