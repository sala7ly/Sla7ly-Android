package com.CyberDunkers.Sla7ly.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.loginState.GetLoginState
import com.CyberDunkers.Sla7ly.presentation.destinations.AuthOptionsDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.OnBoardingScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCases,
    private val getLoginState: GetLoginState,

    ) : ViewModel() {


    fun navigateToNextScreen(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            delay(2000)
        }.invokeOnCompletion {
            viewModelScope.launch {
                appEntryUseCase.getAppEntryUseCase.invoke().collect {
                    if (!it) {
                        navigator.navigate(OnBoardingScreenDestination) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }

                    } else {
                        getLoginState.invoke().collect {loginState ->
                            if (loginState) {
                                navigator.navigate(ClintHomeScreenDestination) {
                                    popUpTo(SplashScreenDestination.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                navigator.navigate(AuthOptionsDestination) {
                                    popUpTo(SplashScreenDestination.route) {
                                        inclusive = true
                                    }
                                }
                            }

                        }

                    }
                }
            }


        }
    }


}