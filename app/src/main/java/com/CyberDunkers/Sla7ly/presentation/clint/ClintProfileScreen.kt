package com.CyberDunkers.Sla7ly.presentation.clint

import CircularIcon
import RoundedBtn
import Sla7lyText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.destinations.AuthOptionsDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintProfileScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ClintProfileScreen(
    navigator: DestinationsNavigator,
    clintViewModel: ClintViewModel = hiltViewModel(),

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Constants.SEC_ORANGE)
    ) {

        Column(
            Modifier
                .weight(0.5f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        /*TODO*/
                    }
                    .align(Alignment.End), alignment = Alignment.TopEnd)
            Sla7lyText(text = "profile Information", modifier = Modifier.align(CenterHorizontally))
        }

        Box(modifier = Modifier.weight(4f)) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp, start = 10.dp, end = 10.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
                    .fillMaxSize()
                    .background(Color.White)
            ) {

                InfoLabel(
                    title = "name",
                    details = "Mohamed Hossam Ibrahim",
                    modifier = Modifier.padding(top = 75.dp)
                )
                InfoLabel(title = "phone", details = "01019326050")
                InfoLabel(title = "address", details = "dakahlya, mansoura, 10 street")
                InfoLabel(title = "email", details = "mohamed.hosaam@gmail.com")
                InfoLabel(title = "Password", details = "..............")


            }
            Box(
                modifier = Modifier
                    .background(Constants.SEC_ORANGE, shape = CircleShape)
                    .size(140.dp)
                    .align(Alignment.TopCenter)
            ) {
                CircularIcon(
                    background = Constants.SEC_ORANGE,
                    resID = R.drawable.profile_pic, modifier = Modifier.align(
                        Alignment.Center
                    ), imgSizeWithDP = 138.dp, onClick = { /*TODO*/ })
            }

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            RoundedBtn(
                onClick = {
                    clintViewModel.logout()
                    navigator.navigate(AuthOptionsDestination.route){
                        popUpTo(ClintProfileScreenDestination.route){
                            inclusive = true
                        }
                    }

                }, text = "Logout", buttonColor = Constants.THIRD_Orange, modifier = Modifier
                    .align(TopCenter)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun InfoLabel(title: String, details: String, modifier: Modifier = Modifier) {
    Column(modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)) {
        Sla7lyText(
            text = title,
            sizeWithSp = 12,
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.ExtraBold
        )
        Box(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = Constants.THIRD_Orange,
                    shape = RoundedCornerShape(size = 11.dp)
                )
        ) {
            Sla7lyText(
                text = details,
                sizeWithSp = 15,
                color = Color.White,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 5.dp)
            )
        }
    }
}