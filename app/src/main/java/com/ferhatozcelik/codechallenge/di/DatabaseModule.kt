package com.ferhatozcelik.codechallenge.di

import android.app.Application
import androidx.room.Room
import com.ferhatozcelik.codechallenge.data.dao.ExampleDao
import com.ferhatozcelik.codechallenge.data.dao.RecipeDao
import com.ferhatozcelik.codechallenge.data.dao.UserDao
import com.ferhatozcelik.codechallenge.data.entity.Recipe
import com.ferhatozcelik.codechallenge.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: AppDatabase.Callback): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "local_database").fallbackToDestructiveMigration().addCallback(callback).build()
    }

    @Provides
    fun provideExampleDao(database: AppDatabase): ExampleDao {
        return database.getExampleDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.getUserDao()
    }

    @Provides
    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.getRecipeDao()
    }
}