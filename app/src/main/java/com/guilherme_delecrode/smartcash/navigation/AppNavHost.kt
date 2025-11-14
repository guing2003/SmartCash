package com.guilherme_delecrode.smartcash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilherme_delecrode.smartcash.ui.screen.history.HistoryScreen
import com.guilherme_delecrode.smartcash.ui.screen.home.HomeScreen
import com.guilherme_delecrode.smartcash.ui.screen.info.AboutScreen
import com.guilherme_delecrode.smartcash.ui.screen.info.FaqScreen
import com.guilherme_delecrode.smartcash.ui.screen.info.PrivacyPolicyScreen
import com.guilherme_delecrode.smartcash.ui.screen.info.TermsOfUseScreen
import com.guilherme_delecrode.smartcash.ui.screen.login.LoginScreen
import com.guilherme_delecrode.smartcash.ui.screen.menu.MenuScreen
import com.guilherme_delecrode.smartcash.ui.screen.register.RegisterInfoScreen
import com.guilherme_delecrode.smartcash.ui.screen.register.RegisterPasswordScreen

@Composable
fun AppNavHost(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppDestinations.Login.route){

        //Login Flow
        composable(AppDestinations.Login.route) {
            LoginScreen(navController)
        }

        //Register Flow
        composable(AppDestinations.RegisterInfo.route){
            RegisterInfoScreen(navController)
        }
        composable(AppDestinations.RegisterPassword.route){
            RegisterPasswordScreen(navController)
        }

        //Home Flow
        composable(AppDestinations.Home.route){
            HomeScreen(navController)
        }

        composable(AppDestinations.Menu.route){
            MenuScreen(navController)
        }

        composable(AppDestinations.History.route){
            HistoryScreen(navController)
        }

        //Comuns Flow
        composable(AppDestinations.Faq.route){
            FaqScreen(navController)
        }
        composable(AppDestinations.Terms.route){
            TermsOfUseScreen(navController)
        }
        composable(AppDestinations.Privacy.route){
            PrivacyPolicyScreen(navController)
        }
        composable(AppDestinations.About.route){
            AboutScreen(navController)
        }

    }
}