package com.example.kaficapp.composable

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
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
import com.example.kaficapp.view_models.CartViewModel


@ExperimentalFoundationApi
@Composable
fun Cakes_Screen(cakeViewModel: CakeViewModel,navHostController: NavHostController,cartViewModel: CartViewModel){
    val cakesType by cakeViewModel.listCakes.collectAsState()
    LaunchedEffect(key1 = true){
        cakeViewModel.getAllCakesTypes()
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

        LazyVerticalGrid(state=cakeViewModel.lazylistState,cells = GridCells.Fixed(2) ,modifier = Modifier.fillMaxSize()){
            items(cakesType.data?: emptyList()){
                Box(modifier= Modifier
                    .background(Color.Red)
                    .height(200.dp)
                    .padding(10.dp)
                    .clickable { navHostController.navigate("Cakes Detail Screen/${it.id}") },contentAlignment = Alignment.Center){
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
fun CakeDetail(cakeViewModel: CakeViewModel,type:Int,cartViewModel: CartViewModel){
    val cakes by cakeViewModel.listCakesType.collectAsState()
    LaunchedEffect(key1 = true){
        if(type!=-1)
            cakeViewModel.getAllCakessByType(type)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {
        LazyVerticalGrid(cells= GridCells.Fixed(2),modifier = Modifier.fillMaxSize()) {
            items(cakes.data?: emptyList()){
                Box(modifier= Modifier
                    .background(Color.Red)
                    .height(200.dp)
                    .padding(10.dp)
                    ,contentAlignment = Alignment.Center){
                    ItemList(id = it.id, name = it.name, onClick = {  cartViewModel.addItem(it) } ,it.price)


                }

            }
        }

    }
}