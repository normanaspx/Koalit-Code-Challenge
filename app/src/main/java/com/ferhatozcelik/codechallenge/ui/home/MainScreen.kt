package com.ferhatozcelik.codechallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.navigation.Screen
import com.ferhatozcelik.codechallenge.ui.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: DetailViewModel) {
    val recipes by viewModel.allRecipes.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Recetas", style = MaterialTheme.typography.headlineMedium) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe) {
                    viewModel.setSelectedRecipe(recipe)
                    navController.navigate(Screen.Detail.route)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la receta (si existe)
            recipe.imageResId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Imagen de la receta",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Título de la receta
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Tiempo de preparación
                Text(
                    text = "Tiempo: ${recipe.prepTime} min",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // Icono de favorito
            Icon(
                imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Favorita",
                tint = if (recipe.isFavorite) Color.Red else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeListScreen() {
    val recipes = listOf(
        Recipe(2,"Pizza Casera", 40, true, android.R.drawable.ic_menu_camera),
        Recipe(3,"Ensalada César", 15, false, android.R.drawable.ic_menu_gallery),
        Recipe(4,"Sopa de Tomate", 25, true) // Sin imagen
    )
    //MainScreen(recipes, navController)
}
