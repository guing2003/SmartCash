package com.guilherme_delecrode.smartcash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.guilherme_delecrode.smartcash.navigation.AppNavHost
import com.guilherme_delecrode.smartcash.ui.theme.SmartCashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartCashTheme {
                AppNavHost()
            }
        }
    }
}