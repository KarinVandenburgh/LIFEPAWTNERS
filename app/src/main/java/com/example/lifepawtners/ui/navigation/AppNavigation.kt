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
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.lifepawtners.ui.chat.ChatScreen
import com.example.lifepawtners.ui.search.SearchScreen
import com.example.lifepawtners.ui.requests.RequestsScreen
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
        composable ("search"){
            SearchScreen()
        }

        composable("create") {
            CreateScreen(navController = navController)
        }
        composable(
            route = "pet_profile_setup/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            PetProfileSetupScreen(
                navController = navController,
                email = email
            )
        }

        composable(
            route = "owner_profile_setup/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            POwnerProfileSetupScreen(
                navController = navController,
                email = email
            )
        }

        composable(
            route = "main/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            MainScreen(
                navController = navController,
                currentUserEmail = email
            )
        }

        composable(
            route = "requests/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            RequestsScreen(
                navController = navController,
                currentOwnerEmail = email
            )
        }
        composable("edit_profile") {
            Text("Edit Profile Screen")
        }

        composable("add_pet") {
            Text("Add Pet Screen")
        }
        // test
        /*composable("debug_home") {
            Text("Login worked")
        }*/

        composable("manage_pets") {
            Text("Manage Pets Screen")
        }

        composable(
            route = "chat/{chatId}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val chatId = backStackEntry.arguments?.getInt("chatId") ?: 0

            val chatName = when (chatId) {
                1 -> "Bella"
                2 -> "Happy Tails Rescue"
                3 -> "Max"
                4 -> "Paws Haven"
                5 -> "Charlie"
                else -> "Chat"
            }

            ChatScreen(
                chatName = chatName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}