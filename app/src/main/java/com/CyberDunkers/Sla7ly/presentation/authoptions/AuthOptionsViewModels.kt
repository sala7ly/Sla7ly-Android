package com.CyberDunkers.Sla7ly.presentation.authoptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.data.models.AppLocal
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.language.LocalUserCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthOptionsViewModels @Inject constructor(
    private val localUserCases: LocalUserCases,
) : ViewModel() {

    init {
        getCurrentLang()
    }

    private  var _buttonStateIsArabic :MutableStateFlow<Boolean> = MutableStateFlow(false)
    val btnStateIsArabic = _buttonStateIsArabic


   private fun saveCurrentLang(language: AppLocal) {
        viewModelScope.launch {
            localUserCases.setLocalUseCase(language)
        }
    }


    private fun getCurrentLang() {

        viewModelScope.launch {
            localUserCases.getLocalUseCase.invoke().onEach {
                when (it) {
                    AppLocal.AR -> {
                        _buttonStateIsArabic.value = true
                    }

                    AppLocal.EN -> {
                        _buttonStateIsArabic.value = false
                    }
                }
            }
        }
    }

    fun changeLang() {
        _buttonStateIsArabic.value = !_buttonStateIsArabic.value
        if (_buttonStateIsArabic.value)
            saveCurrentLang(AppLocal.AR)
        else
            saveCurrentLang(AppLocal.EN)

    }

}
