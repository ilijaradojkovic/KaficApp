package com.example.kaficapp.view_models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaficapp.Repository
import com.example.kaficapp.composable.CreateToast
import com.example.kaficapp.data.LoginInfo
import com.example.kaficapp.data.RespondHandler
import com.example.kaficapp.data.RespondInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repository: Repository,private val context: Context) :ViewModel() {


    val usernameLogin= mutableStateOf("")

    val passwordLogin = mutableStateOf("")

    fun changePassword(password:String){
        passwordLogin.value=password
    }
    fun changeUsername(username:String){
        usernameLogin.value=username
    }

    private val _authDataLogin= MutableStateFlow<RespondHandler<RespondInfo>>(RespondHandler.Loading())
    val authDataLogin = _authDataLogin

    fun tryToLogin() {
        if(CheckData()){
            CreateToast(message = "Field Error",context = context)
            return
        }
        _authDataLogin.value=RespondHandler.Loading()

        viewModelScope.launch (Dispatchers.IO){
            try {
                repository.tryToLogin(LoginInfo(usernameLogin.value, passwordLogin.value)).collect {
                    _authDataLogin.value=RespondHandler.Success<RespondInfo>(it,"OK")

                }
            }
            catch (e:Exception){
                _authDataLogin.value=RespondHandler.Error("${e.message}")
            }

        }

    }
    fun restartLogin(){
        _authDataSignUp.value=RespondHandler.Loading()
    }

    private fun CheckData(): Boolean {
        val emailReg=Regex("[0-9a-z]+@gmail\\.com")
        val resultEmail= emailReg.matchEntire(usernameLogin.value as CharSequence)

      return resultEmail==null
    }
    //signup
    //-----------------------------------
    val usernameSignup= mutableStateOf("")
    val passwordSignup = mutableStateOf("")
    val passwordRepeatSignup = mutableStateOf("")
    private val _authDataSignUp= MutableStateFlow<RespondHandler<RespondInfo>>(RespondHandler.Loading())
    val authDataSignUp = _authDataSignUp

    fun changePasswordSignUp(password:String){
        passwordSignup.value=password
    }
    fun changeUsernameSignUp(username:String){
        usernameSignup.value=username
    }

    fun changePasswordRepeatSignUp(passwordRepeat:String){
        passwordRepeatSignup.value=passwordRepeat
    }
    fun restartSignUp(){
        _authDataSignUp.value=RespondHandler.Loading()
    }

    fun tryToSignUp(){

        if(CheckDataSignUp()){

            CreateToast(message = "Field Error",context = context)
            return
        }
        _authDataSignUp.value=RespondHandler.Loading()

        viewModelScope.launch (Dispatchers.IO){
            try {
                repository.tryToSignUp(LoginInfo(usernameSignup.value, passwordSignup.value)).collect {
                    _authDataSignUp.value=RespondHandler.Success<RespondInfo>(it,"OK")

                }
            }
            catch (e:Exception){
                _authDataSignUp.value=RespondHandler.Error("${e.message}")
            }

        }
    }

    private fun CheckDataSignUp(): Boolean {
        val emailReg=Regex("[0-9a-z]+@gmail\\.com")
        val resultEmail= emailReg.matchEntire(usernameSignup.value as CharSequence)
        val areTheSame=passwordRepeatSignup.value.equals(passwordSignup.value)
        return resultEmail==null ||  !areTheSame
    }
}