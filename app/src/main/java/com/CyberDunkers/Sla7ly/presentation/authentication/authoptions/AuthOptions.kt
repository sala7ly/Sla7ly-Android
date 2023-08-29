package com.CyberDunkers.Sla7ly.presentation.authentication.authoptions

import Sla7lyText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.common.LanguageHelper
import com.CyberDunkers.Sla7ly.data.models.applocal.AppLocal
import com.CyberDunkers.Sla7ly.presentation.authentication.authoptions.components.ChipItem
import com.CyberDunkers.Sla7ly.presentation.navigation.ScreenRoutes
import kotlinx.coroutines.delay

@Composable
fun AuthOptions(
    navController: NavController,
    viewModels: AuthOptionsViewModels = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val resources = LocalContext.current.resources
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val isVisible = remember {
            mutableStateOf(false)
        }
        val weight by animateFloatAsState(
            targetValue = if (!isVisible.value) 0.4f else 1f,
            label = "", animationSpec = tween(800)
        )
        val langIsAr = remember {
            mutableStateOf(false)
        }

        val height = remember { mutableStateOf(0.2f) }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Constants.SEC_ORANGE,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )


                )
                .weight(weight)

        ) {


            LaunchedEffect(isVisible.value) {
                // Hide the box for 200 milliseconds
                if (!isVisible.value) {
                    delay(1)
                    isVisible.value = true
                }
                height.value = 1f
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                AnimatedVisibility(
                    modifier = Modifier
                        .fillMaxWidth(),
                    visible = isVisible.value,
                    enter = slideInHorizontally(
                        initialOffsetX = { -1000 }, animationSpec = tween(1000)
                    ) + fadeIn(),
                ) {
                    ImageShape2()

                    ImageShape(
                        modifier = Modifier

                    )
                    ImageShape(
                        modifier = Modifier
                            .padding(top = 50.dp)

                    )
                }
            }


            Image(
                modifier = Modifier
                    .width(262.dp)
                    .height(75.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.logopng),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            LanguageSwitcher(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp),
                isArabic = langIsAr
             , onSwitchOn = {
                    viewModels.saveCurrentLang(AppLocal.AR)
                } , onSwitchOff = {
                    viewModels.saveCurrentLang(AppLocal.EN)
                })


        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(color = Color.White)
        ) {
            item {
                AnimatedVisibility(
                    modifier = Modifier
                        .fillMaxWidth(),
                    visible = isVisible.value,
                    enter = fadeIn(tween(1000)),
                ) {
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
                                .weight(1f)
                                .background(Constants.MAIN_ORANGE)
                        )
                        Row(
                            modifier = Modifier.weight(2f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Sla7lyText( text = stringResource(R.string.login_as),
                                sizeWithSp = 25)
                        }

                        Spacer(
                            modifier = Modifier
                                .height(2.dp)
                                .weight(1f)
                                .background(Constants.MAIN_ORANGE)
                        )
                    }
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    val selectedChapIsWorker = remember {
                        mutableStateOf(false)
                    }
                    val selectedChipIsClint = remember {
                        mutableStateOf(false)
                    }
                    ChipSection(selectedChipIsClint, selectedChapIsWorker, isVisible)

                    NextButton {
                        if (selectedChapIsWorker.value){
                            navController.navigate(ScreenRoutes.WorkerLogin.route)
                        }
                        if(selectedChipIsClint.value) {
                            navController.navigate(ScreenRoutes.ClintLogin.route)
                        }

                    }
                }
            }

        }


    }
}



@Composable
fun ImageShape2() {


    val isCircle = remember {
        mutableStateOf(true)
    }
    val borderRadius by animateIntAsState(
        targetValue = if (isCircle.value) 60 else 45, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow,

            ),

        )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .animateContentSize()

    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier

                .width(100.dp)
                .height(100.dp)
                .background(color = Color(0x9EFFFFFF), shape = RoundedCornerShape(borderRadius))
                .clickable(interactionSource = interactionSource, indication = null) {
                    isCircle.value = !isCircle.value
                }
                .padding(top = 50.dp, end = 50.dp)
                .align(Alignment.TopEnd)

        )

        // Other content in the parent Box
    }
}

@Composable
fun ImageShape(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(73.dp)
                .align(Alignment.TopEnd),

            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = ""
        )
    }
}


@Composable
fun LanguageSwitcher(
    modifier: Modifier = Modifier,
    isArabic: MutableState<Boolean>,
     onSwitchOn: () -> Unit,
     onSwitchOff: () -> Unit,
) {

    Box(
        modifier = modifier // Adjust the width as needed
            .wrapContentHeight()
    ) {
        Switch(

            checked = isArabic.value,
            onCheckedChange = { isChecked ->
                isArabic.value = isChecked
                 if (isChecked) {
                    onSwitchOn()
                } else {
                    onSwitchOff()
                }
            },
            modifier = Modifier
                .toggleable(value = isArabic.value, onValueChange = {
                    isArabic.value = it
                })
                .clickable {
                    isArabic.value = !isArabic.value
                },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Constants.MAIN_ORANGE,
                uncheckedThumbColor = Constants.MAIN_ORANGE,
                uncheckedTrackColor = Color.White,
                uncheckedBorderColor = Color.White

            )
        )
        Text(
            text = if (isArabic.value) "en" else "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp, bottom = 3.dp),
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        Text(
            text = if (!isArabic.value) "ar" else "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp, bottom = 4.dp),
            fontWeight = FontWeight.ExtraBold,
            color = Constants.MAIN_ORANGE
        )
    }

}

@Composable
fun ChipSection(
    selectedChapIsClint: MutableState<Boolean>,
    selectedChapIsWorker: MutableState<Boolean>,
    isVisible: MutableState<Boolean>,

    ) {

    val colorOfClint = remember {
        mutableStateOf(Color.White)
    }
    val colorOfWorker = remember {
        mutableStateOf(Color.White)
    }


    if (selectedChapIsClint.value && !selectedChapIsWorker.value) {
        colorOfClint.value = Constants.MAIN_ORANGE
        colorOfWorker.value = Color.White
    } else if (!selectedChapIsClint.value && selectedChapIsWorker.value) {
        colorOfWorker.value = Constants.MAIN_ORANGE
        colorOfClint.value = Color.White
    } else {
        colorOfClint.value = Color.White
        colorOfWorker.value = Color.White
    }




    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        item {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { -1000 }, animationSpec = tween(1000)
                ) + fadeIn(),
            ) {
                val worker = ChipData(R.drawable.worker, stringResource(R.string.worker))
                ChipItem(
                    chipData = worker, modifier = Modifier
                        .width(175.dp)
                        .padding(10.dp)
                        .border(
                            width = 2.dp,
                            color = colorOfWorker.value,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    selectedChapIsWorker.value = true
                    selectedChapIsClint.value = false
                }
            }

        }

        item {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { 1000 }, animationSpec = tween(1000)
                ) + fadeIn(),
            ) {
                val clint = ChipData(R.drawable.clint, stringResource(R.string.clint))
                ChipItem(
                    chipData = clint,
                    modifier = Modifier
                        .width(175.dp)
                        .padding(10.dp)
                        .border(
                            width = 2.dp,
                            color = colorOfClint.value,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    selectedChapIsClint.value = true
                    selectedChapIsWorker.value = false

                }
            }

        }

    }


}

@Composable
fun NextButton(
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
       shape = RoundedCornerShape(100.dp), colors = ButtonDefaults.buttonColors(
            Constants.SEC_ORANGE
        ), modifier = Modifier
            .width(172.dp)
            .padding(top = 30.dp, bottom = 10.dp)
    ) {
        Sla7lyText(text = "Next" , fontWeight = FontWeight.ExtraBold , sizeWithSp = 20 )
    }
}
