package com.ferhatozcelik.codechallenge.data.entity

data class Recipe(
    val title: String,
    val prepTime: Int,
    val isFavorite: Boolean,
    val imageResId: Int? = null
)