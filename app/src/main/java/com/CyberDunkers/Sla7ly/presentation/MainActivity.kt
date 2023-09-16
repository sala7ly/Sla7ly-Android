package com.CyberDunkers.Sla7ly.presentation

import LoadingPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.bottom_nav.Parent
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintFavScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintProfileScreenDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Constants.SEC_ORANGE)
            systemUiController.setNavigationBarColor(Color.Black)
            // A surface container using the 'background' color from the theme


            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root)
                Parent()
            }
        }
    }
}
