package com.example.foodiedelivery.data.model

data class Restaurant(
    val id: Int,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val deliveryTimeText: String,
    val imageUrl: String,
    val isFeatured: Boolean = false
)
