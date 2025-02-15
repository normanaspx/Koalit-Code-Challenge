package com.ferhatozcelik.codechallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ferhatozcelik.codechallenge.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE email = :email and password= :password")
    suspend fun getUserByEmail(email: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)
}