package com.example.lifepawtners.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lifepawtners.ui.auth.CreateScreen
import com.example.lifepawtners.ui.auth.LoginScreen
import com.example.lifepawtners.ui.swipe.SwipeScreen
import com.example.lifepawtners.ui.chat.MessagesScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("create") {
            CreateScreen(navController = navController)
        }

        composable("swipe") {
            SwipeScreen()
        }

        composable("messages") {
            MessagesScreen()
        }
    }
}