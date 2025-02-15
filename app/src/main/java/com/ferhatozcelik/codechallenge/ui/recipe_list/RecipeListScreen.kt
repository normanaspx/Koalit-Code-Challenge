package com.ferhatozcelik.codechallenge.ui.recipe_list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.navigation.Screen
import com.ferhatozcelik.codechallenge.ui.recipe_add.RecipeViewModel
import com.ferhatozcelik.jetpackcomposetemplate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(navController: NavHostController, viewModel: RecipeViewModel) {
    val context = LocalContext.current
    var hasReadPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_IMAGES  // Usar para Android 13+
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Lanza la solicitud de permiso usando el launcher
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasReadPermission = isGranted
    }

    var showRationaleDialog by remember { mutableStateOf(false) }

    // Verifica el permiso al cargar la pantalla
    LaunchedEffect(key1 = hasReadPermission) {
        if (!hasReadPermission) {
            val activity = context as? androidx.activity.ComponentActivity
            if (activity != null &&
                ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_MEDIA_IMAGES)) {
                // Si es necesario, muestra la justificación
                showRationaleDialog = true
            } else {
                // Si no se necesita justificación, solicita el permiso
                launcher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
    }

    // Muestra el diálogo de justificación si el usuario ha rechazado previamente el permiso
    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog = false },
            title = { Text("Permiso necesario", fontWeight = FontWeight.Bold) },
            text = { Text("Se necesita permiso para acceder a las imágenes de las recetas.") },
            confirmButton = {
                Button(onClick = {
                    showRationaleDialog = false
                    launcher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }) {
                    Text("OK")
                }
            }
        )
    }

    // Si el permiso es concedido, muestra el contenido de la app
    if (hasReadPermission) {
        val recipes by viewModel.allRecipes.observeAsState(emptyList())

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lista de Recetas") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Screen.RecipeAdd.route)
                        }) {
                            Icon(Icons.Filled.Add, contentDescription = "Agregar receta")
                        }
                    }
                )
            }
        )  { paddingValues ->
            LazyColumn(modifier = Modifier.fillMaxSize() .padding(paddingValues)) {
                items(recipes) { recipe ->
                    RecipeCard(recipe) {
                        viewModel.setSelectedRecipe(recipe)
                        navController.navigate(Screen.RecipeDetail.route)
                    }
                }
            }
        }
    } else if (!showRationaleDialog) {
        // Si no se tiene el permiso, muestra un mensaje
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Se requiere permiso para acceder a las imágenes.")
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            AsyncImage(
                model = recipe.imageUri,
                contentDescription = recipe.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box( // Degradado
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = recipe.description,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = Color.Red,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
