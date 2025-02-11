package com.ferhatozcelik.codechallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val exampleRepository: ExampleRepository) : ViewModel() {
    private fun createUser() {
        viewModelScope.launch {
            userRepository.insertUser("admin", "admin")
        }
    }

}