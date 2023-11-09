package com.CyberDunkers.Sla7ly.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.AppEntryUseCase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCase.invoke()
        }
    }


}