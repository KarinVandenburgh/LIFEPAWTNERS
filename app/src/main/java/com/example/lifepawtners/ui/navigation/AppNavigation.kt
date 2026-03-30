package com.example.lifepawtners.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lifepawtners.ui.auth.CreateScreen
import com.example.lifepawtners.ui.auth.LoginScreen
import com.example.lifepawtners.ui.main.MainScreen
import com.example.lifepawtners.ui.pOwner.POwnerProfileSetupScreen
import com.example.lifepawtners.ui.swipe.SwipeScreen
import com.example.lifepawtners.ui.pet.PetProfileSetupScreen
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        //change back to login
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("create") {
            CreateScreen(navController = navController)
        }
        composable("pet_profile_setup") {
            PetProfileSetupScreen(navController)
        }
        composable("owner_profile_setup"){
            POwnerProfileSetupScreen(navController)
        }

        composable("main") {
            MainScreen()
        }
        composable("swipe") {
            SwipeScreen()
        }
    }
}