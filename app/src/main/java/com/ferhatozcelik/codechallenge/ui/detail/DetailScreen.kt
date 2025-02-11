package com.ferhatozcelik.codechallenge.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(recipeId: Int, viewModel: DetailViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }
    var recipe by remember { mutableStateOf<Recipe?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding alrededor de la pantalla
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Ingresa tu receta",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp) // Espacio debajo del título
        )

        // Campo de texto para el título de la receta
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(recipe!!.title) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo de texto para la descripción de la receta
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3 // Permitir varias líneas para la descripción
        )

        // Campo para el tiempo de preparación (en minutos)
        OutlinedTextField(
            value = prepTime,
            onValueChange = { prepTime = it },
            label = { Text("Tiempo de preparación (en minutos)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Acciones al completar la entrada del teclado */ }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Opción para marcar como favorita
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Marcar como favorita")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isFavorite,
                onCheckedChange = { isFavorite = it }
            )
        }

        // Botón para guardar la receta
        Button(
            onClick = {
                val recipe = Recipe(
                    title = title,
                    prepTime =  prepTime.toInt(),
                    isFavorite =  isFavorite,
                    imageResId =  null
                )
                viewModel.createRecipe(recipe = recipe)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Receta")
        }
    }
}

