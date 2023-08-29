package com.CyberDunkers.Sla7ly.presentation.authentication.authoptions

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.data.models.applocal.AppLocal
import com.CyberDunkers.Sla7ly.domin.usecase.language.LocalUserCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
import javax.inject.Inject
@HiltViewModel
class AuthOptionsViewModels @Inject constructor(
    private val localUserCases: LocalUserCases
) : ViewModel() {

    private val _lang = MutableLiveData<AppLocal>()
    val lang = _lang

    init {
        getCurrentLang()
    }

    fun saveCurrentLang(language: AppLocal) {
        viewModelScope.launch {
            localUserCases.setLocalUseCase(language)
        }
    }

     private fun getCurrentLang(){

         viewModelScope.launch {
       localUserCases.getLocalUseCase.invoke().onEach {
            when(it){
                AppLocal.AR ->{
                    _lang.value = AppLocal.AR
                }
                AppLocal.EN ->{
                    _lang.value = AppLocal.EN
                }
            }
        }
         }
    }



}