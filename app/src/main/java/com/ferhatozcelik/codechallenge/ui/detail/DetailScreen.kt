package com.ferhatozcelik.codechallenge.ui.detail

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferhatozcelik.codechallenge.data.entity.Recipe

@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val recipe by viewModel.selectedRecipe.observeAsState()
    val recipes by viewModel.allRecipes.observeAsState(emptyList())

    // Lógica para seleccionar una imagen desde la galería
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri = uri
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles de la receta") },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para navegar atrás */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Padding alrededor de la pantalla
                    .padding(paddingValues),
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
                    label = { Text("Título de la receta") },
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

                // Botón para seleccionar imagen
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar imagen")
                }

                // Mostrar la imagen seleccionada (si existe)
                imageUri?.let {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier.fillMaxWidth().height(200.dp) // Ajusta el tamaño de la imagen
                    )
                }

                // Botón para guardar la receta
                Button(
                    onClick = {
                        val recipe = Recipe(
                            title = title,
                            prepTime = prepTime.toInt(),
                            isFavorite = isFavorite,
                            imageUri = imageUri // Guardamos la URI de la imagen
                        )
                        viewModel.createRecipe(recipe = recipe)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Receta")
                }
            }
        }
    )
}


