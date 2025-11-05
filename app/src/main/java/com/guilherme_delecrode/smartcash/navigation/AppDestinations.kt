package com.guilherme_delecrode.smartcash.navigation

sealed class AppDestinations(val route: String){

    object Login : AppDestinations("login")
}