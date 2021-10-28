package com.example.kaficapp.composable

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Cake
import com.example.Drink
import com.example.kaficapp.Repository
import com.example.kaficapp.data.CakeType
import com.example.kaficapp.data.DrinkType
import com.example.kaficapp.data.RespondHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CakeViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    val lazylistState by  mutableStateOf(LazyListState())

        private val _listCakes= MutableStateFlow<RespondHandler<List<CakeType>>>(RespondHandler.Loading())
        val listCakes=_listCakes

        private val _listCakesByType= MutableStateFlow<RespondHandler<List<Cake>>>(RespondHandler.Loading())
        val listCakesType=_listCakesByType


        fun getAllCakesTypes(){

            viewModelScope.launch(Dispatchers.IO) {
                _listCakes.value= RespondHandler.Loading()
                try{
                    repository.getAllCakesTypes().collect {
                        _listCakes.value= RespondHandler.Success(it,"Success")

                    }
                }
                catch (e: Exception){
                    _listCakes.value= RespondHandler.Error(e.message?:"")
                }

            }

        }
        fun getAllCakessByType(type:Int){
            viewModelScope.launch(Dispatchers.IO) {
                _listCakesByType.value= RespondHandler.Loading()
                try{
                    repository.getAllCakesByType(type).collect {


                        _listCakesByType.value= RespondHandler.Success(it,"Success")

                    }
                }
                catch (e: Exception){
                    _listCakesByType.value= RespondHandler.Error(e.message?:"")
                }

            }
        }


}