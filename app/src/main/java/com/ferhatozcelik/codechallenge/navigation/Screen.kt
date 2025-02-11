package com.ferhatozcelik.codechallenge.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Detail : Screen("detail_screen")
    object Login : Screen("login_screen")
}