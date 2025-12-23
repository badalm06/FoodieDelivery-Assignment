package com.example.foodiedelivery.data.remote

import com.example.foodiedelivery.data.remote.dto.RecipeResponseDto
import retrofit2.http.GET

interface RecipeApiService {

    @GET("recipes")
    suspend fun getRecipes(): RecipeResponseDto
}
