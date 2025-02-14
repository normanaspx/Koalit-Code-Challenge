package com.ferhatozcelik.codechallenge.navigation

sealed class Screen(val route: String) {
    object RecipeList : Screen("list_screen")
    object RecipeAdd : Screen("add_screen")
    object RecipeDetail : Screen("detail_screen")
    object Login : Screen("login_screen")
}