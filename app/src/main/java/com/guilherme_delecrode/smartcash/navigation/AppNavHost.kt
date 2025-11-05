package com.guilherme_delecrode.smartcash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.ui.screen.login.LoginScreen

@Composable
fun AppNavHost(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppDestinations.Login.route){
        composable(AppDestinations.Login.route) {
            LoginScreen(navController = navController)
        }

    }
}