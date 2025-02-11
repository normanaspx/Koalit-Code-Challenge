package com.ferhatozcelik.codechallenge.data.entity

data class Recipe(
    val title: String,
    val prepTime: Int, // Tiempo de preparación en minutos
    val isFavorite: Boolean,
    val imageResId: Int? = null // Puede ser null si no hay imagen
)