package com.guilherme_delecrode.smartcash.navigation

sealed class AppDestinations(val route: String){

    //Login Flow
    object Login : AppDestinations("login")

    //Register Flow
    object RegisterInfo : AppDestinations("register_info")
    object RegisterPassword : AppDestinations("register_password")

    //Home Flow
    object Home: AppDestinations("home")
    object Menu: AppDestinations("menu")
    object History : AppDestinations("history")


    //Comuns Screen flow
    object Faq : AppDestinations("faq")
    object Terms : AppDestinations("terms")
    object Privacy : AppDestinations("privacy")
    object About : AppDestinations("about")
}