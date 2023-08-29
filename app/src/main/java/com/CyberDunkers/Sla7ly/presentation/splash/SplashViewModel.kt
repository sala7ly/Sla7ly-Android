package com.CyberDunkers.Sla7ly.presentation.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.GetAppEntryUseCase
import com.CyberDunkers.Sla7ly.presentation.navigation.ScreenRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val appEntryUseCase: AppEntryUseCases
): ViewModel() {



     fun navigateToNextScreen(navController: NavController){
         viewModelScope.launch {
            delay(2000)
        }.invokeOnCompletion {
             viewModelScope.launch {
                 appEntryUseCase.getAppEntryUseCase.invoke().collect{
                     if (!it){
                         navController.navigate(ScreenRoutes.OnBoardingScreen.route){
                             popUpTo(ScreenRoutes.SplashScreen.route){
                                 inclusive = true
                             }
                         }
                     }else {
                         navController.navigate(ScreenRoutes.AuthOptions.route){
                             popUpTo(ScreenRoutes.SplashScreen.route){
                                 inclusive = true
                             }
                         }
                     }
                 }
             }


       }
    }


}