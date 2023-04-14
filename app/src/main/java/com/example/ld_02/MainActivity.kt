package com.example.ld_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ld_02.data.MovieDatabase
import com.example.ld_02.data.MovieRepository
import com.example.ld_02.models.getMovies
import com.example.ld_02.navigation.SetupNavGraph
import com.example.ld_02.ui.theme.GreyBlack
import com.example.ld_02.ui.theme.Ld02Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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