package com.ferhatozcelik.codechallenge.navigation

import android.R
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.local.UserSessionManager
import com.ferhatozcelik.codechallenge.ui.detail.DetailScreen
import com.ferhatozcelik.codechallenge.ui.home.MainScreen
import com.ferhatozcelik.codechallenge.ui.login.LoginScreen

@Composable
fun NavGraph(navController: NavHostController, sessionManager: UserSessionManager) {

    val startDestination = if (sessionManager.isUserLoggedIn()) Screen.Main.route else Screen.Login.route

    NavHost(
        navController = navController, startDestination = startDestination
    ) {

        val recipes = listOf(
            Recipe(2,"Pizza Casera", 40, true, R.drawable.ic_menu_camera),
            Recipe(3,"Ensalada César", 15, false, R.drawable.ic_menu_gallery),
            Recipe(4,"Sopa de Tomate", 25, true) // Sin imagen
        )
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, sessionManager = sessionManager)
        }
        composable("detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
            if (recipeId != null) {
                DetailScreen(recipeId)
            }
        }
    }
}
