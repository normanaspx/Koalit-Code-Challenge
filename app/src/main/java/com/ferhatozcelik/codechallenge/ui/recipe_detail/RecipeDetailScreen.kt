package com.ferhatozcelik.codechallenge.ui.recipe_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ferhatozcelik.codechallenge.ui.recipe_add.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(viewModel: RecipeViewModel, navController: NavController) {
    val recipe by viewModel.selectedRecipe.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe?.title ?: "Detalles") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        recipe?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(it.title, style = MaterialTheme.typography.headlineLarge)
                Text("Tiempo: ${it.prepTime} min", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                if (it.imageUri?.isNotEmpty() == true) {
                    AsyncImage(
                        model = it.imageUri,
                        contentDescription = "Imagen de la receta",
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )
                }
            }
        } ?: run {
            Text("Cargando receta...", modifier = Modifier.padding(16.dp))
        }
    }
}
