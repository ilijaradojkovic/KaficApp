package com.example.kaficapp.composable.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kaficapp.Constants
import com.example.kaficapp.LoginPage
import com.example.kaficapp.SetScreenOne
import com.example.kaficapp.composable.*
import com.example.kaficapp.view_models.AuthenticationViewModel
import com.example.kaficapp.view_models.CartViewModel
import com.example.kaficapp.view_models.DrinksViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun setUpNavigation(
    navHostController: NavHostController,
    authenticationViewModel: AuthenticationViewModel,
    drinksViewModel: DrinksViewModel,
    cakeViewModel: CakeViewModel,
    cartViewModel: CartViewModel
) {
    NavHost(navController = navHostController, startDestination = Constants.LOING_SCREEN) {
        composable(route = Constants.SIGN_UP_SCREEN) {
            SignUp(authenticationViewModel) {
                navHostController.navigate(Constants.LOING_SCREEN) {
                    popUpTo(Constants.SIGN_UP_SCREEN) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
                }
            }

            composable(route = Constants.LOING_SCREEN) {
                LoginPage(authViewModel = authenticationViewModel, context = LocalContext.current, {
                    navHostController.navigate(
                        route = Constants.SIGN_UP_SCREEN)
                    //{
//                        popUpTo(Constants.LOING_SCREEN) {
//                            inclusive = true
//                        }
//                        launchSingleTop = true
//                    }
                }, {
                    navHostController.navigate(
                        route = Constants.MENU_SCREEN
                    )
                    //{popUpTo(Constants.LOING_SCREEN){
////                inclusive=true
////            }
////                launchSingleTop=true}
                })
            }

            composable(route = Constants.MENU_SCREEN) {
                SetScreenOne(navHostController,drinksViewModel,cakeViewModel,cartViewModel)

            }
        composable(route= Constants.DRINKS_DETAIL, listOf(navArgument("id",{
        type= NavType.IntType
        nullable=false
    }))){

        val id= it.arguments?.getInt("id")?:-1

        DrinkType(drinksViewModel = drinksViewModel, type =id ,cartViewModel)

    }
        composable(route= Constants.CAKES_DETAIL, listOf(navArgument("id",{
            type= NavType.IntType
            nullable=false
        }))){

            val id= it.arguments?.getInt("id")?:-1

            CakeDetail(cakeViewModel = cakeViewModel, type =id,cartViewModel )

        }

        }
    }
