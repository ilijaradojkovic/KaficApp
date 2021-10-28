package com.example.kaficapp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import com.example.kaficapp.composable.CakeViewModel
import com.example.kaficapp.composable.navigation.setUpNavigation
import com.example.kaficapp.ui.theme.KaficAppTheme
import com.example.kaficapp.view_models.AuthenticationViewModel
import com.example.kaficapp.view_models.CartViewModel
import com.example.kaficapp.view_models.DrinksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authenticationViewModel:AuthenticationViewModel by viewModels()
    private  val cakeViewModel:CakeViewModel by viewModels()
    private val drinksViewModel:DrinksViewModel by viewModels()
    private val cartViewModel:CartViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController= rememberNavController()
            KaficAppTheme {
                setUpNavigation(navHostController,authenticationViewModel,drinksViewModel,cakeViewModel,cartViewModel)

            }
        }
    }
}


