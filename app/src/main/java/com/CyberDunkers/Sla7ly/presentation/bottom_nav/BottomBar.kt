package com.CyberDunkers.Sla7ly.presentation.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.presentation.NavGraphs
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintBookingScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintFavScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintProfileScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigateTo

@Composable
fun BottomBar(
    navController: NavController,
) {
    val currentDestination  = navController.currentDestination?.route ?: ClintHomeScreenDestination.route
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

        BottomNavigation (
            backgroundColor = Color.White ,
            modifier = Modifier.padding(12.dp)
                .fillMaxWidth().clip(RoundedCornerShape(25.dp)) ,
            elevation = 10.dp

            ){
            BottomBarDestination.values().forEach { destination ->

                BottomNavigationItem(
                    selected = currentDestination == destination.direction.route,
                    modifier = Modifier.background(Color.White , RoundedCornerShape(25.dp) ),
                    onClick = {
                        navController.navigateTo(destination.direction) {
                            popUpTo(currentDestination){
                                inclusive = true
                            }
                        }
                    },

                    icon = {
                        val selected =  destination.direction.route == currentDestination

                        Icon(
                            destination.icon, modifier = Modifier
                                .background(if (selected)Constants.THIRD_Orange else Color.White, shape = CircleShape)
                                .padding(5.dp), contentDescription = "", tint = if (selected)  Color.White else Color.Black
                        )
                    },
                    selectedContentColor = Constants.THIRD_Orange,
                    unselectedContentColor = Color.Black,
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