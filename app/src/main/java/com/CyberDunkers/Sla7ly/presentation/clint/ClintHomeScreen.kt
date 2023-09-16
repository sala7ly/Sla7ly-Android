package com.CyberDunkers.Sla7ly.presentation.clint

import ImageShape4
import OutLineTextFieldSearch
import Sla7lyText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.bottom_nav.Parent
import com.CyberDunkers.Sla7ly.presentation.clint.components.WorkerItem
import com.CyberDunkers.Sla7ly.presentation.clint.components.WorkerItemData
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay

@Destination
@Composable
fun ClintHomeScreen(
    navController: NavController,
) {

    val remNavController = rememberNavController()
    val nameOfUser = "mohamed"
    val searchText = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.background(Constants.SEC_ORANGE)) {
        Box(modifier = Modifier.weight(1f)) {
            ImageShape4()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        /*TODO*/
                    })
                Image(
                    painter = painterResource(id = R.drawable.profile_pic), contentDescription = "",
                    modifier = Modifier.clip(CircleShape)
                )

            }
            Column(modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp)) {
                Sla7lyText(
                    text = "what do you want to fix ,",
                    textAlign = TextAlign.End,
                    sizeWithSp = 15
                )
                Sla7lyText(
                    text = nameOfUser,
                    color = Constants.MAIN_ORANGE,
                    textAlign = TextAlign.End,
                    sizeWithSp = 19
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )

                OutLineTextFieldSearch(
                    onNameChange = {
                        searchText.value = it
                    }, label = "search for craftsman you need ...", modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }


        }
        val isVisible = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(isVisible.value) {
            // Hide the box for 200 milliseconds
            if (!isVisible.value) {
                delay(1)
                isVisible.value = true
            }
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            visible = isVisible.value,
            enter = slideInVertically(
                initialOffsetY = { 1000 }, animationSpec = tween(1000)
            ) + fadeIn(tween(1000)),
        ) {
            LazyColumn(modifier = Modifier.padding(start = 10.dp , end = 10.dp)) {
                item {

                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }

                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
                item {
                    val data = WorkerItemData(
                        R.drawable.wood_worker,
                        "wood Worker",
                        "+100 craftsman have an experience in this field"
                    )
                    WorkerItem(workerItemData = data) {

                    }
                }
            }
        }
    }

    @Composable
    fun InfinitelyPulsingHeart() {
        // Creates an [InfiniteTransition] instance for managing child animations.
        val infiniteTransition = rememberInfiniteTransition()

        // Creates a child animation of float type as a part of the [InfiniteTransition].
        val scale by infiniteTransition.animateFloat(
            initialValue = 3f,
            targetValue = 6f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
    }
}