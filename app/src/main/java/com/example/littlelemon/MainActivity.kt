package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittlelemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyNavigation()



        }
    }
}

@Composable
fun MyNavigation() {
    val context= LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    fun saveData(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)}
    var firstname=getData("firstname")
    val loggedIn = firstname?.isNotEmpty() == false

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (loggedIn) {
            OnBoarding.route
        } else {
            Home.route
        }
    )
     {
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }


    }
}