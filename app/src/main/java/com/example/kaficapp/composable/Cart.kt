package com.example.kaficapp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kaficapp.R
import com.example.kaficapp.view_models.CartViewModel


@Composable
fun Cart_Screen(cartViewModel: CartViewModel){
    val items by cartViewModel.cart.collectAsState()
    val sum = cartViewModel.SumPrice
Column(modifier=Modifier.fillMaxSize()) {
    LazyColumn(modifier=Modifier.fillMaxSize().padding(vertical = 10.dp)){
        items(items){
            CartItem_Ele(it.name,it.price,{cartViewModel.removeItem(it)})
            Spacer(modifier=Modifier.height(10.dp))
        }
        item{
            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically){
                Text("Ukupno : $sum")
            }
        }
    }

}
}

@Composable
fun CartItem_Ele(name:String,price:Int,onClick:()->Unit){
    Card(modifier= Modifier
        .fillMaxWidth()
        .height(90.dp)
       ,shape = RoundedCornerShape(10.dp)){
        Row(modifier = Modifier.fillMaxSize()) {

            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="" ,modifier=Modifier.weight(1f),contentScale = ContentScale.FillWidth)
            Column(modifier= Modifier
                .weight(2f)
                .fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.SpaceAround){
                Text("$name", color=Color.DarkGray,fontSize = 20.sp)
                Text("Cena : $price",color=Color.DarkGray,fontSize = 15.sp)
            }
            IconButton(onClick = {onClick() },modifier = Modifier.weight(1f).fillMaxHeight()) {
                Icon(Icons.Filled.Delete,"")

            }

        }
    }

}