package com.ferhatozcelik.codechallenge.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.codechallenge.data.local.UserSessionManager
import com.ferhatozcelik.codechallenge.ui.recipe_add.RecipeAddScreen
import com.ferhatozcelik.codechallenge.ui.recipe_add.RecipeViewModel
import com.ferhatozcelik.codechallenge.ui.recipe_list.RecipeListScreen
import com.ferhatozcelik.codechallenge.ui.login.LoginScreen
import com.ferhatozcelik.codechallenge.ui.recipe_detail.RecipeDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    sessionManager: UserSessionManager
) {
    val viewModel: RecipeViewModel = hiltViewModel()
    val startDestination = if (sessionManager.isUserLoggedIn()) Screen.RecipeList.route else Screen.RecipeList.route

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, sessionManager = sessionManager)
        }
        composable(Screen.RecipeList.route) {
            RecipeListScreen(navController = navController, viewModel)
        }
        composable(Screen.RecipeAdd.route) {
            RecipeAddScreen(viewModel, navController)
        }
        composable(Screen.RecipeDetail.route) {
            RecipeDetailScreen(viewModel, navController)
        }
    }
}
