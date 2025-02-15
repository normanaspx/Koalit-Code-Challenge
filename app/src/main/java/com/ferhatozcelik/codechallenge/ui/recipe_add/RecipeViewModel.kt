package com.ferhatozcelik.codechallenge.ui.recipe_add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val allRecipes: LiveData<List<Recipe>> = recipeRepository.getAllRecipes()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe

    fun setSelectedRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }
    fun createRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeRepository.insert(recipe)
        }
    }
}