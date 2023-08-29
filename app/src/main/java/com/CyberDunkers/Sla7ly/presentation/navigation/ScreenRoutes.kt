package com.CyberDunkers.Sla7ly.presentation.navigation

sealed class ScreenRoutes(val route : String){
    object SplashScreen : ScreenRoutes("splash_screen")
    object OnBoardingScreen : ScreenRoutes("onBoarding")
    object AuthOptions : ScreenRoutes("AuthOptions")
    object WorkerLogin : ScreenRoutes("WorkerLogin")
    object WorkerSignUp : ScreenRoutes("WorkerSignUp")
    object ClintLogin : ScreenRoutes("ClintLogin")
    object ClintSignUp : ScreenRoutes("ClintSignUp")


}
