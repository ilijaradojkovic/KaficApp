package com.example.kaficapp.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kaficapp.R
import com.example.kaficapp.data.RespondHandler
import com.example.kaficapp.view_models.AuthenticationViewModel

@Composable
fun SignUp(authViewModel: AuthenticationViewModel, navigateToLogin: () -> Unit){
    val username by authViewModel.usernameSignup
    val password by authViewModel.passwordSignup
    val passwordRepeat by authViewModel.passwordRepeatSignup
    val respondHandler  by  authViewModel.authDataSignUp.collectAsState()
    LaunchedEffect(key1 = respondHandler){
        when(respondHandler){
            is RespondHandler.Loading->{
                Log.i("cao","Loading")}
            is RespondHandler.Success->{

                authViewModel.restartSignUp()
                navigateToLogin()
            }
            is RespondHandler.Error->{
                Log.i("cao",respondHandler.message)}
        }
    }
    Image(painter = painterResource(id = R.drawable.caffebbcg),
        contentDescription ="Bcg",modifier = Modifier
            .fillMaxSize()
            .background(Color.White),contentScale = ContentScale.FillHeight )
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(3f),contentAlignment = Alignment.BottomCenter){
            TextField(value = username,label = {Text("Username")}, onValueChange ={authViewModel.changeUsernameSignUp(it)},shape = RoundedCornerShape(15.dp),colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent,errorIndicatorColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,unfocusedIndicatorColor = Color.Transparent,textColor = Color.Black,backgroundColor = Color.White))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),contentAlignment = Alignment.Center){
            TextField(value = password,label = { Text("Password") }, onValueChange ={authViewModel.changePasswordSignUp(it)},shape = RoundedCornerShape(15.dp),colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent,errorIndicatorColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,unfocusedIndicatorColor = Color.Transparent,textColor = Color.Black,backgroundColor = Color.White))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(2f),contentAlignment = Alignment.TopCenter){
            TextField(value = passwordRepeat,label = {Text("Password Repeat")}, onValueChange ={authViewModel.changePasswordRepeatSignUp(it)},shape = RoundedCornerShape(15.dp),colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent,errorIndicatorColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,unfocusedIndicatorColor = Color.Transparent,textColor = Color.Black,backgroundColor = Color.White))
        }
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),contentAlignment = Alignment.TopCenter){
            Button(onClick = { authViewModel.tryToSignUp() },modifier = Modifier.fillMaxWidth(0.4f),colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)) {

                Text("Sign up",color = Color.White)
            }
        }

    }
}