package com.ferhatozcelik.codechallenge.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.entity.User
import com.ferhatozcelik.codechallenge.data.repository.ExampleRepository
import com.ferhatozcelik.codechallenge.data.repository.RecipeRepository
import com.ferhatozcelik.codechallenge.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>> = recipeRepository.getAllRecipes()

}