package com.example.foodiedelivery.data.repository

import com.example.foodiedelivery.data.model.Restaurant
import com.example.foodiedelivery.data.remote.RecipeApiService

class RestaurantRepository(private val api: RecipeApiService) {

    suspend fun getRestaurants(): List<Restaurant> {
        val response = api.getRecipes()
        return response.recipes.map { dto ->
            Restaurant(
                id = dto.id,
                name = dto.name,
                cuisine = dto.cuisine ?: "Mixed",
                rating = dto.rating ?: 0.0,
                deliveryTimeText = "${dto.prepTimeMinutes ?: 20} min • ₹${(dto.prepTimeMinutes ?: 20) * 5}",
                imageUrl = dto.image ?: "",
                isFeatured = (dto.rating ?: 0.0) >= 4.5
            )
        }
    }
}
