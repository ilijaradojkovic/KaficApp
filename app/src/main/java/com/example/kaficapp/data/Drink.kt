package com.example

import kotlinx.serialization.Serializable


@Serializable
data class Drink(  val id: Int,
                   var name: String,
                   var price: Int,
                   var type: Int)



