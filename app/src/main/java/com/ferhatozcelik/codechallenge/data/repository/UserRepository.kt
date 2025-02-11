package com.ferhatozcelik.codechallenge.data.repository

import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getUserByEmail(email: String, password: String) = userDao.getUserByEmail(email, password)

    suspend fun insertUser(username: String, password: String) {
        val user = User(id = 1, email= username, password = password)
        userDao.insertUser(user)
    }
}