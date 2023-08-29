package com.CyberDunkers.Sla7ly.presentation.onBoarding

import Sla7lyText
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.navigation.ScreenRoutes
import com.CyberDunkers.Sla7ly.presentation.onBoarding.components.OnBoardingPage
import com.CyberDunkers.Sla7ly.presentation.onBoarding.components.PageIndicator
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
     viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pageState = rememberPagerState {
        listOfPages.size
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(state = pageState) { index ->
            OnBoardingPage(page = listOfPages[index])
        }
        Row(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding(), horizontalArrangement = Arrangement.Center
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = listOfPages.size,
                selectedPage = pageState.currentPage,
            )
        }
        val scope = rememberCoroutineScope()

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (pageState.currentPage == 0) {

                Spacer(modifier = Modifier.width(0.7.dp))

                NextButton({
                    scope.launch {
                        pageState.animateScrollToPage(page = pageState.currentPage + 1 , animationSpec =  tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        ))
                    }
                })

            } else if (pageState.currentPage == 2) {

                BackButton({
                    scope.launch {
                        pageState.animateScrollToPage(page = pageState.currentPage - 1, animationSpec =  tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        ))
                    }
                })

                FinishButton({
                    scope.launch {
                        pageState.animateScrollToPage(page = pageState.currentPage + 1 , animationSpec =  tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        ))
                    }
                    navController.navigate(ScreenRoutes.AuthOptions.route){

                            popUpTo(ScreenRoutes.OnBoardingScreen.route) {
                                inclusive = true
                            }

                    }
                    viewModel.saveAppEntry()

                })

            } else {

                BackButton({
                    scope.launch {
                        pageState.animateScrollToPage(page = pageState.currentPage - 1, animationSpec =  tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        ))
                    }
                })
                NextButton({
                    scope.launch {
                        pageState.animateScrollToPage(page = pageState.currentPage + 1 , animationSpec =  tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        ) )
                    }
                })

            }

        }


    }


}

@Composable
fun NextButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(20.dp)
            .clip(CircleShape)
            .background(Constants.MAIN_ORANGE)
    ) {

        val iconPainter = painterResource(id = R.drawable.next_page)
        Image(
            painter = iconPainter,
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier.size(50.dp) // Adjust the size as needed
        )
    }
}

@Composable
fun FinishButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(25.dp))
            .width(100.dp)
            .background(Constants.MAIN_ORANGE)
    ) {

        val iconPainter = painterResource(id = R.drawable.next_page)
        Sla7lyText(text = "Finish", fontWeight = FontWeight.ExtraBold, color = Color.White , sizeWithSp = 15)
    }
}

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(onClick = onClick, modifier = modifier.padding(20.dp)) {
        Sla7lyText(text = "Back", color = Color.Gray , sizeWithSp = 14)
    }
}
