package com.example.kaficapp


import com.example.Cake
import com.example.Drink
import com.example.kaficapp.data.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

//mora http da impl zato nije htelo
    //kad se radi mora da vrati json pa zato ne moze :String jer api vraca json a ne string a gsonconverterfactory odmah pretvori morali bi da imamo psosebnu klasu koja ima string kao atribut
    @GET("/Hello")
    suspend fun  getHello(): LoginInfo

    @POST("/Login")
    suspend fun  tryToLogin(
        @Body body: LoginInfo
    ): RespondInfo

    @POST("/Register")
    suspend fun  tryToSignUp(
        @Body body:LoginInfo
    ):RespondInfo

    @GET("/allDrinkTypes")
    suspend fun  getAllDrinkTypes(): List<DrinkType>

    //nije isto kad asaljemo query param preko retorfita jer moramo tamo u ktor da stavimo url bez /{id}
    @GET("/allDrinks")
    suspend fun  getAllDrinksByType(@Query("id") type:Int):List<Drink>


    @GET("/allCakesTypes")
    suspend fun  getAllCakeTypes(): List<CakeType>

    //nije isto kad asaljemo query param preko retorfita jer moramo tamo u ktor da stavimo url bez /{id}
    @GET("/allCakes")
    suspend fun  getAllCakesByType(@Query("id") type:Int):List<Cake>
}