package com.ferhatozcelik.codechallenge.data.repository

import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.User
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getUserByEmail(email: String, password: String) = userDao.getUserByEmail(email, password)

    suspend fun insertUser(username: String, password: String) {
        val user = User(id = 1, email= username, password = password)
        userDao.insertUser(user)
    }
}