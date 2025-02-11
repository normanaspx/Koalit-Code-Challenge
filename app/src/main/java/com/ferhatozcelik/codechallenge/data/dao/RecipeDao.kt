package com.ferhatozcelik.codechallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ferhatozcelik.codechallenge.data.entity.ExampleEntity
import com.ferhatozcelik.codechallenge.data.entity.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

}