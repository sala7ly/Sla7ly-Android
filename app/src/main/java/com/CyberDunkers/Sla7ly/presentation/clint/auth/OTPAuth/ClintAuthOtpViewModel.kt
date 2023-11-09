package com.CyberDunkers.Sla7ly.presentation.clint.auth.OTPAuth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CyberDunkers.Sla7ly.common.util.Resource
import com.CyberDunkers.Sla7ly.data.models.AuthVerifyBody
import com.CyberDunkers.Sla7ly.data.models.UpdateUserBody
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import com.CyberDunkers.Sla7ly.domin.usecase.AppSettings.userToken.TokenUseCases
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.ClintRegistrationUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.ClintVerifyUseCase
import com.CyberDunkers.Sla7ly.domin.usecase.clint.auth.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClintAuthOtpViewModel @Inject constructor(
   private val clintRegistrationUseCase: ClintRegistrationUseCase ,
   private val clintVerifyUseCase: ClintVerifyUseCase ,
   private val updateUserUseCase: UpdateUserUseCase ,
    private val settingLocalDataSource: SettingLocalDataSource ,
    private val setTokenUseCases: TokenUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ClintAuthState())
    val state =  _state

    private val _phone = MutableLiveData<String>()
    val phone: MutableLiveData<String> =  _phone

init {
    println("from init block ${_phone.value}")
}

    fun phoneRequest(phone : String) {
            clintRegistrationUseCase(phone).onEach{
                when(it){
                    is Resource.Success->{
                        state.value = ClintAuthState(isDataSent = true )
                        _phone.value = phone
                        println(_phone.value)
                    }
                    is Resource.Loading->{
                        state.value = ClintAuthState(isLoading = true)
                    }
                    is Resource.Error->{
                        state.value = ClintAuthState(error = it.message ?: "error not found")
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun verifyUser(codeVerification : String, phone : String){
            val authVerifyBody = AuthVerifyBody(phone , codeVerification)
            clintVerifyUseCase(authVerifyBody).onEach {
                when(it){
                    is Resource.Success->{
                        state.value = ClintAuthState(data = it.data)
                    }
                    is Resource.Loading->{
                        state.value = ClintAuthState(isLoading = true)
                    }
                    is Resource.Error->{
                        state.value = ClintAuthState(error = it.message ?: "error not found")
                    }
                }
            }.launchIn(viewModelScope)


    }

    fun UpdateUser( phone : String ,firstName : String , lastName : String , token : String ){
        val userBody = UpdateUserBody( phone = phone ,first_name = firstName , last_name = lastName , token = token )
        updateUserUseCase(userBody).onEach {

            println(userBody)

            when(it){
                is Resource.Success->{
                    state.value = ClintAuthState(isDataSent = true)
                    saveUserLogin(token)
                }
                is Resource.Loading->{
                    state.value = ClintAuthState(isLoading = true)
                }
                is Resource.Error->{
                    state.value = ClintAuthState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)


    }

     fun saveUserLogin(token: String){
         viewModelScope.launch {
             settingLocalDataSource.saveLoginState()
         }
    }


}