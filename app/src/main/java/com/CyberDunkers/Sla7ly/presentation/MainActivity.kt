package com.CyberDunkers.Sla7ly.presentation

import InfoDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.data.repository.NetworkConnectivityObserver
import com.CyberDunkers.Sla7ly.domin.repository.ConnectivityObserver
import com.CyberDunkers.Sla7ly.presentation.clint.bottom_nav.Parent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Constants.Blue2)
            systemUiController.setNavigationBarColor(Color.Black)
            // A surface container using the 'background' color from the theme

            val infoDialog = remember { mutableStateOf(true) }
            val firstLost = remember { mutableStateOf(true) }
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Available
            )
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {

                if (status != ConnectivityObserver.Status.Available && infoDialog.value) {
                    InfoDialog(
                        title = "Whoops!",
                        desc = "No Internet Connection found.\n" +
                                "Check your connection or try again.",
                        onDismiss = {
                            infoDialog.value = false
                            firstLost.value = false
                        }

                    )


                }

                if (status == ConnectivityObserver.Status.Available && !firstLost.value) {
                    InfoDialog(
                        title = "Back Online",
                        desc = "your connection is back again",
                        imgId = R.drawable.outline_wifi_24,
                        onDismiss = {
                            infoDialog.value = true
                            firstLost.value = true
                        }
                    )

                }
                LaunchedEffect(key1 = Unit) {
                    if (!infoDialog.value) {
                        lifecycleScope.launch {
                            delay(5000)
                        }.invokeOnCompletion {
                            infoDialog.value = true
                        }
                    }
                }

                DestinationsNavHost(navGraph = NavGraphs.root)
                Parent()

            }
        }
    }
}
