package com.CyberDunkers.Sla7ly.presentation.authoptions

import Sla7lyText
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.authoptions.components.ChipItem
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintLoginScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.WorkerLoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun AuthOptions(
    navigator: DestinationsNavigator,
    viewModels: AuthOptionsViewModels = hiltViewModel()
) {



    val buttonStateIsArabic = remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()
    LaunchedEffect(scope) {
        viewModels.btnStateIsArabic.collect{
            when(it){
                true ->{
                    buttonStateIsArabic.value = true
                }
                false ->{
                    buttonStateIsArabic.value = false
                }
            }
            Log.d("button state is ......." , buttonStateIsArabic.value.toString())
        }

    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Constants.Blue2)
    ) {
        val isVisible = remember {
            mutableStateOf(false)
        }
        val weight by animateFloatAsState(
            targetValue = if (!isVisible.value) 0.4f else 1f,
            label = "", animationSpec = tween(800)
        )

        val height = remember { mutableStateOf(0.2f) }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
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


            Image(
                modifier = Modifier
                    .width(262.dp)
                    .height(75.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.home_logo_org),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            LanguageSwitcher(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp),
                isArabic = buttonStateIsArabic
             , onSwitchOn = {
                    viewModels.changeLang()

                } , onSwitchOff = {
                    viewModels.changeLang()
                })


        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(color = Constants.Blue2)
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
                                .background(Constants.Main_Yellow)
                        )
                        Row(
                            modifier = Modifier.weight(2f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Sla7lyText( text = stringResource(R.string.login_as),
                                sizeWithSp = 25 , color = Color.White)
                        }

                        Spacer(
                            modifier = Modifier
                                .height(2.dp)
                                .weight(1f)
                                .background(Constants.Main_Yellow)
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
                          navigator.navigate(WorkerLoginScreenDestination)
                        }
                        if(selectedChipIsClint.value) {
                          navigator.navigate(ClintLoginScreenDestination)
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
fun ImageShape(modifier: Modifier = Modifier , resId : Int = R.drawable.ellipse) {

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(73.dp)
                .align(Alignment.TopEnd),

            painter = painterResource(resId),
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
        colorOfClint.value = Constants.Main_Yellow
        colorOfWorker.value = Color.White
    } else if (!selectedChapIsClint.value && selectedChapIsWorker.value) {
        colorOfWorker.value = Constants.Main_Yellow
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
                            width = 6.dp,
                            color = colorOfWorker.value,
                            shape = RoundedCornerShape(15.dp)
                        )
                , textColor = colorOfWorker.value) {
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
                            width = 6.dp,
                            color = colorOfClint.value,
                            shape = RoundedCornerShape(15.dp)
                        )
                , textColor = colorOfClint.value ) {
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
            Color.White
        ), modifier = Modifier
            .width(172.dp)
            .padding(top = 30.dp, bottom = 10.dp)
    ) {
        Sla7lyText(text = "Next" , fontWeight = FontWeight.ExtraBold , sizeWithSp = 20 )
    }
}
