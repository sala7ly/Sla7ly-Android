package com.CyberDunkers.Sla7ly.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.CyberDunkers.Sla7ly.presentation.authentication.authoptions.AuthOptions
import com.CyberDunkers.Sla7ly.presentation.authentication.clint.login.ClintLoginScreen
import com.CyberDunkers.Sla7ly.presentation.authentication.worker.login.WorkerLoginScreen
import com.CyberDunkers.Sla7ly.presentation.authentication.worker.signup.WorkerSignUpScreen
import com.CyberDunkers.Sla7ly.presentation.onBoarding.OnBoardingScreen
import com.CyberDunkers.Sla7ly.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.route) {
        composable(ScreenRoutes.SplashScreen.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { SplashScreen(navController = navController) }

        composable(ScreenRoutes.OnBoardingScreen.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { OnBoardingScreen(navController = navController ) }

        composable(ScreenRoutes.AuthOptions.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { AuthOptions(navController = navController) }

        composable(ScreenRoutes.WorkerLogin.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { WorkerLoginScreen(navController = navController) }

        composable(ScreenRoutes.WorkerSignUp.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { WorkerSignUpScreen(navController = navController) }

        composable(ScreenRoutes.ClintLogin.route,
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300)) }
        ) { ClintLoginScreen(navController = navController) }

    }
}
