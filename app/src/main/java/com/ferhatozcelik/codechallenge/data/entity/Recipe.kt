package com.ferhatozcelik.codechallenge.data.entity

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val prepTime: Int,
    val isFavorite: Boolean,
    val imageUri: String?
) : Parcelable