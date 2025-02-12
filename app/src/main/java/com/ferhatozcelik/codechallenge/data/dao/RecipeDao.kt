package com.ferhatozcelik.codechallenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ferhatozcelik.codechallenge.data.entity.ExampleEntity
import com.ferhatozcelik.codechallenge.data.entity.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getRecipeById(id: Int): LiveData<Recipe?>

}