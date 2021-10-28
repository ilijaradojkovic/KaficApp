package com.example.kaficapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kaficapp.composable.CakeViewModel
import com.example.kaficapp.composable.Cakes_Screen
import com.example.kaficapp.composable.Cart_Screen
import com.example.kaficapp.composable.DrinksScreen
import com.example.kaficapp.data.Selection
import com.example.kaficapp.view_models.CartViewModel
import com.example.kaficapp.view_models.DrinksViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SetScreenOne(
    navHostController: NavHostController,
    drinksViewModel: DrinksViewModel,
    cakeViewModel: CakeViewModel,
    cartViewModel: CartViewModel
) {
    val scaffoldState = rememberScaffoldState()
    var selected by remember {
        mutableStateOf(Selection.DRINKS)
    }

    Scaffold(scaffoldState = scaffoldState,modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.BCG)),topBar = {CreateTabLayout(selected,{selected=it})}) {


       if (selected==Selection.DRINKS){

          DrinksScreen(drinksViewModel = drinksViewModel,navHostController)
       }
        else if(selected==Selection.CAKES){
          Cakes_Screen(cakeViewModel,navHostController,cartViewModel)
       }else{
            Cart_Screen(cartViewModel)
       }
    }

}

@Composable
fun CreateTabLayout(selected: Selection,changeSelection:(Selection)->Unit) {


    val animCircleDrinks by animateFloatAsState(targetValue = if(selected==Selection.DRINKS) 80f else 0f, tween(500))
    val animCircleCakes by animateFloatAsState(targetValue = if(selected==Selection.CAKES) 80f else 0f, tween(500))
    val animCircleCart by animateFloatAsState(targetValue = if(selected==Selection.CART) 80f else 0f, tween(500))

    TabRow(selectedTabIndex = selected.ordinal,modifier = Modifier.fillMaxWidth(),backgroundColor = colorResource(
        id = R.color.BCG
    )) {
        Tab(selected = selected==Selection.DRINKS,onClick = {changeSelection(Selection.DRINKS);},selectedContentColor = Color.White,modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawCircle(Color.White, radius = animCircleDrinks)
            }){
            Box(modifier = Modifier.wrapContentWidth(),contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.drink),"Drinks Img")
            }

        }
        Tab(selected = selected==Selection.CAKES,onClick = {changeSelection(Selection.CAKES);},selectedContentColor = Color.White,modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawCircle(Color.White, radius = animCircleCakes)
            }){
            Image(painter = painterResource(id = R.drawable.cake),"Cakes Img")
        }
        Tab(selected = selected==Selection.CART,onClick = {changeSelection(Selection.CART);},selectedContentColor = Color.White,modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawCircle(Color.White, radius = animCircleCart)
            }){
            Image(painter = painterResource(id = R.drawable.cart),"Cart Img")
        }
    }
}

