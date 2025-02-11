package com.ferhatozcelik.codechallenge.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.entity.User
import com.ferhatozcelik.codechallenge.data.repository.ExampleRepository
import com.ferhatozcelik.codechallenge.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _loginState = mutableStateOf<Boolean?>(null)
    val loginState: State<Boolean?> get() = _loginState

    init {
        createUser()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmail(email, password)
            _loginState.value = user != null
        }
    }

    private fun createUser() {
        viewModelScope.launch {
           userRepository.insertUser("admin", "admin")
        }
    }
}