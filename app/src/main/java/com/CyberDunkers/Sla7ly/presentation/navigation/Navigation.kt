package com.CyberDunkers.Sla7ly.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.route) {
        composable(ScreenRoutes.SplashScreen.route) { SplashScreen(navController = navController) }

    }
}
