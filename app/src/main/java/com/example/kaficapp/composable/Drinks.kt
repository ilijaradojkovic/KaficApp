package com.example.kaficapp.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kaficapp.R
import com.example.kaficapp.data.RespondHandler
import com.example.kaficapp.view_models.CartViewModel
import com.example.kaficapp.view_models.DrinksViewModel

@ExperimentalFoundationApi
@Composable
fun DrinksScreen(drinksViewModel: DrinksViewModel, navHostController: NavHostController){

    val typeDrinks by drinksViewModel.listDrinks.collectAsState()
    LaunchedEffect(key1 = true){

            if(typeDrinks is RespondHandler.Loading)
                drinksViewModel.getAllDrinkTypes()
    }
    LaunchedEffect(key1 = typeDrinks){
        when(typeDrinks){
            is RespondHandler.Error->{Log.i("cao",typeDrinks.message)}
            is RespondHandler.Success->{}
            is RespondHandler.Loading->{}
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)) {
        LazyVerticalGrid(state=drinksViewModel.lazylistState,cells = GridCells.Fixed(2) ,modifier = Modifier.fillMaxSize()){
            items(typeDrinks.data?: emptyList()){


                Box(modifier= Modifier
                    .background(Color.Red)
                    .height(200.dp)
                    .padding(10.dp).clickable { navHostController.navigate("Drinks Detail Screen/${it.id}") }
                    ,contentAlignment = Alignment.Center){
                    Card(modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),backgroundColor = colorResource(id = R.color.BCG)) {
                        // Image(painter = painterResource(id = R.drawable.coffie), contentDescription = "",contentScale = ContentScale.FillHeight)
                        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                            Text(it.name,color = Color.White,fontSize = 20.sp,textAlign = TextAlign.Center)
                        }

                    }

                }
            }

        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DrinkType(drinksViewModel: DrinksViewModel,type:Int,cartViewModel: CartViewModel){
    val drinks by drinksViewModel.listDrinksType.collectAsState()

    LaunchedEffect(key1 = true){
        if(type!=-1)
            drinksViewModel.getAllDrinksByType(type)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)) {
        LazyVerticalGrid(cells=GridCells.Fixed(2),modifier = Modifier.fillMaxSize()) {
            items(drinks.data?: emptyList()){

                ItemList(id = it.id, name = it.name, onClick = {  cartViewModel.addItem(it) } ,it.price)


            }
        }
    }
}