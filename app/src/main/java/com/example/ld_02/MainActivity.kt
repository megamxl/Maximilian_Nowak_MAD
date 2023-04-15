package com.example.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ld_02.navigation.SetupNavGraph
import com.example.ld_02.ui.theme.Ld02Theme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ld02Theme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}