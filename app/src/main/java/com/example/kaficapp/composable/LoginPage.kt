package com.example.kaficapp

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.kaficapp.data.RespondHandler
import com.example.kaficapp.view_models.AuthenticationViewModel


@Composable
fun LoginPage(authViewModel: AuthenticationViewModel, context: Context, navigateToSignUp: () -> Unit,navigateToMenu:()->Unit){
    val username by authViewModel.usernameLogin
    val password by authViewModel.passwordLogin
    val respondHandler  by  authViewModel.authDataLogin.collectAsState()
    LaunchedEffect(key1 = respondHandler){
       when(respondHandler){
           is RespondHandler.Loading->{Log.i("cao","Loading")}
           is RespondHandler.Success->{
               authViewModel.restartLogin()
               navigateToMenu()

           }
            is RespondHandler.Error->{Log.i("cao",respondHandler.message)}
       }
    }
    Box(modifier= Modifier
        .fillMaxSize()
    ) {

        Image(painter = painterResource(id = R.drawable.caffebbcg), contentDescription = "Bcg",contentScale = ContentScale.FillHeight,modifier = Modifier.fillMaxSize())


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),contentAlignment = Alignment.Center){
                val lotti =
                    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.coffieanim))
                val progress by animateLottieCompositionAsState(
                    composition = lotti.value,
                    isPlaying = true,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(composition = lotti.value, progress = progress,modifier = Modifier.size(150.dp))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),contentAlignment = Alignment.BottomCenter){
                TextField(value = username,label = {Text("Email")}, onValueChange ={authViewModel.changeUsername(it)},shape = RoundedCornerShape(15.dp)
                    ,colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent,errorIndicatorColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,unfocusedIndicatorColor = Color.Transparent,textColor = Color.Black,backgroundColor = Color.White))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),contentAlignment = Alignment.Center){
                TextField(value = password,label = {Text("Password")}, onValueChange ={authViewModel.changePassword(it)},shape = RoundedCornerShape(15.dp),colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent,errorIndicatorColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,unfocusedIndicatorColor = Color.Transparent,textColor = Color.Black,backgroundColor = Color.White))
            }
            Box(modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),contentAlignment = Alignment.BottomCenter){
                Button(onClick = { navigateToMenu() ;
                  //  authViewModel.tryToLogin()
                                 },modifier = Modifier.fillMaxWidth(0.4f),colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)) {

                    Text("Login",color = Color.White)
                }
            }
            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(10.dp),verticalAlignment = Alignment.Top,horizontalArrangement = Arrangement.Center){
                Text("Dont have an account?")
                ClickableText(text = buildAnnotatedString {

                                                          withStyle(SpanStyle(colorResource(id = R.color.purple_500))){
                                                              append("Sign up")
                                                          }
                }, onClick = {

                    navigateToSignUp()
                })
                }
            }

        }
    }
