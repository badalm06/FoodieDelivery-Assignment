package com.example.foodiedelivery.data.remote.dto

data class RecipeDto(
    val id: Int,
    val name: String,
    val cuisine: String?,
    val image: String?,
    val rating: Double?,
    val prepTimeMinutes: Int?
    // there are more fields in API, but these are enough [web:37]
)
