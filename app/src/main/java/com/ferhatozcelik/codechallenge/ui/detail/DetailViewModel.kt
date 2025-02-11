package com.ferhatozcelik.codechallenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.repository.ExampleRepository
import com.ferhatozcelik.codechallenge.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _selectedRecipe = MutableLiveData<Recipe?>()
    val selectedRecipe: LiveData<Recipe?> = _selectedRecipe
    fun createRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeRepository.insert(recipe)
        }
    }

    fun loadRecipeById(id: Int) {
        viewModelScope.launch {
            _selectedRecipe.postValue(recipeRepository.getRecipeById(id))
        }
    }
}