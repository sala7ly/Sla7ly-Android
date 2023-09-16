package com.CyberDunkers.Sla7ly.presentation.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.NavGraphs
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintBookingScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintFavScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintProfileScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.Destination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigateTo

@Composable
fun BottomBar(
    navController: NavController,
) {
    val currentDestination: NavDestination? = navController.currentDestination
    val onBack: () -> Unit = {
        navController.popBackStack()
    }
    val screens = listOf(
        ClintHomeScreenDestination,
        ClintFavScreenDestination,
        ClintBookingScreenDestination,
        ClintProfileScreenDestination
    )
    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }
    if (showBottomBar) {

        BottomNavigation {
            BottomBarDestination.values().forEach { destination ->
                val tint = if (currentDestination == destination.direction) {
                    Constants.SEC_ORANGE // Use orange tint if selected
                } else {
                    Color.Black // Use black tint if not selected
                }
                BottomNavigationItem(
                    selected = currentDestination == destination.direction,
                    modifier = Modifier.background(Color.White),
                    onClick = {
                        navController.navigateTo(destination.direction) {
                            launchSingleTop = true
                        }
                    },

                    icon = { Icon(destination.icon, modifier = Modifier.background(Constants.SEC_ORANGE , shape = CircleShape).padding(5.dp) ,contentDescription = "" , tint = tint) },
                    label = { Text(destination.label) },
                    selectedContentColor = Constants.MAIN_ORANGE ,
                    unselectedContentColor = Color.Black ,
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Parent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }

    ) {
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
    }
}

fun checkForDestinations(
    navigationRoutes: Array<BottomBarDestination>,
    navBackStackEntry: NavBackStackEntry,
): Boolean {
    navigationRoutes.forEach {
        if (it.direction.route == navBackStackEntry.destination.route) {
            return true
        }

    }
    return false
}