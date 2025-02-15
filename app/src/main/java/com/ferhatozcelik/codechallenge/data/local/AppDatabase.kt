package com.ferhatozcelik.codechallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ferhatozcelik.codechallenge.data.dao.RecipeDao
import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.entity.User
@Database(
    entities = [
        User::class,
        Recipe::class
               ],
    version = 6)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    abstract fun getRecipeDao(): RecipeDao

}