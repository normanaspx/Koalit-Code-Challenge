package com.ferhatozcelik.codechallenge.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.codechallenge.data.local.UserSessionManager
import com.ferhatozcelik.codechallenge.ui.detail.DetailScreen
import com.ferhatozcelik.codechallenge.ui.detail.DetailViewModel
import com.ferhatozcelik.codechallenge.ui.home.MainScreen
import com.ferhatozcelik.codechallenge.ui.login.LoginScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    sessionManager: UserSessionManager
) {
    val viewModel: DetailViewModel = hiltViewModel()
    val startDestination = if (sessionManager.isUserLoggedIn()) Screen.Main.route else Screen.Login.route

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController = navController, viewModel)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, sessionManager = sessionManager)
        }
        composable(Screen.Detail.route) {
            DetailScreen(viewModel)
        }
    }
}
