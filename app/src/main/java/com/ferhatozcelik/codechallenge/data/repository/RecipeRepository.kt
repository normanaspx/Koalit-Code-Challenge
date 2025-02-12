package com.ferhatozcelik.codechallenge.data.repository

import androidx.lifecycle.LiveData
import com.ferhatozcelik.codechallenge.data.dao.RecipeDao
import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.entity.User
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
}
