package com.ferhatozcelik.codechallenge.data.repository

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import com.ferhatozcelik.codechallenge.data.dao.RecipeDao
import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.entity.User
import java.io.File
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }
    suspend fun getRecipeById(id: Int): LiveData<Recipe?> {
        return recipeDao.getRecipeById(id) // Llamada síncrona (No es suspend)
    }

    fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes() // LiveData se maneja automáticamente en la UI
    }

    // Método para guardar la imagen en el almacenamiento

}
