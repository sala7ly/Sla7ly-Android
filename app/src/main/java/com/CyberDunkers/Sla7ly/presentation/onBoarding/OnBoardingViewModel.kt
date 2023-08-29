package com.CyberDunkers.Sla7ly.presentation.onBoarding

import android.app.Application
import android.content.res.Configuration
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.data.repository.LocalUserManagerImpl
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.AppEntryUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.AppEntryUseCase.SaveAppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
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