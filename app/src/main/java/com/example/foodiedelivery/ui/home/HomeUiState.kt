package com.example.foodiedelivery.ui.home

import com.example.foodiedelivery.data.model.Restaurant

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val restaurants: List<Restaurant>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
