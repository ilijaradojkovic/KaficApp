package com.example.kaficapp.composable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kaficapp.R
import com.example.kaficapp.data.CartItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ItemList(id:Int,name:String,onClick:(CartItem)->Unit,price:Int){


var plusState by remember{ mutableStateOf(false)}
    var animatefloat = animateDpAsState(targetValue = if(!plusState) 30.dp else 15.dp, tween(500))
    var rotateion= animateFloatAsState(targetValue = if(!plusState) 0f else 360f,tween(500))

    LaunchedEffect(key1 = plusState, block = {
        Log.i("cao",plusState.toString())
        if(plusState==true)
            delay(300)
            plusState=false

    })


    Box(modifier= Modifier
        .height(200.dp)
        .padding(10.dp)

        ,contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center), backgroundColor = colorResource(id = R.color.BCG)
        ) {
            //Image(painter = painterResource(id = R.drawable.coffie),"img",contentScale = ContentScale.FillBounds)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), contentAlignment = Alignment.CenterEnd
                ) {
                    IconButton(onClick = { plusState=true ;onClick(CartItem(name, price)) }) {
                        Icon(
                            Icons.Default.Add,
                            "Add",
                            tint = Color.White,
                            modifier = Modifier.size(animatefloat.value).rotate(rotateion.value)
                        )
                    }

                }
                Text(
                    name,
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    price.toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )
            }


    }

    }
}