package com.example.kaficapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kaficapp.data.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel  @Inject constructor():ViewModel() {

    val cart=MutableStateFlow<ArrayList<CartItem>>(ArrayList<CartItem>())
    var SumPrice by mutableStateOf(0)

    public fun addItem(item:CartItem){
    cart.value.add(item)
        SumPrice+=item.price
    }
    public fun removeItem(cartItem: CartItem) {
        SumPrice-=cartItem.price
        cart.value.remove(cartItem)

    }
}