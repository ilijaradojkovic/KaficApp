package com.example.kaficapp.view_models

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Drink
import com.example.kaficapp.Repository
import com.example.kaficapp.data.DrinkType

import com.example.kaficapp.data.LoginInfo
import com.example.kaficapp.data.RespondHandler
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(private val repository: Repository) :ViewModel() {
    val lazylistState by  mutableStateOf(LazyListState())
    private val _listDrinks= MutableStateFlow<RespondHandler<List<DrinkType>>>(RespondHandler.Loading())
    val listDrinks=_listDrinks

    private val _listDrinksByType= MutableStateFlow<RespondHandler<List<Drink>>>(RespondHandler.Loading())
    val listDrinksType=_listDrinksByType


    fun getAllDrinkTypes(){
    viewModelScope.launch(Dispatchers.IO) {
        _listDrinks.value=RespondHandler.Loading()
        try{
        repository.getAllDrinkTypes().collect {
_listDrinks.value=RespondHandler.Success(it,"Success")
            _listDrinks.value= RespondHandler.Success(it,"Success")
        }
        }
        catch (e:Exception){
            _listDrinks.value=RespondHandler.Error(e.message?:"")
        }

    }

}
    fun getAllDrinksByType(type:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _listDrinksByType.value=RespondHandler.Loading()
            try{
                repository.getAllDrinksByType(type).collect {


                    _listDrinksByType.value=RespondHandler.Success(it,"Success")

                }
            }
            catch (e:Exception){
                _listDrinksByType.value=RespondHandler.Error(e.message?:"")
            }

        }
    }

}