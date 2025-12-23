package com.example.foodiedelivery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiedelivery.data.remote.RetrofitInstance
import com.example.foodiedelivery.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = RestaurantRepository(RetrofitInstance.api)

    private val _uiState = MutableLiveData<HomeUiState>()
    val uiState: LiveData<HomeUiState> = _uiState

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        _uiState.value = HomeUiState.Loading

        viewModelScope.launch {
            try {
                val restaurants = repository.getRestaurants()
                _uiState.value = HomeUiState.Success(restaurants)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load restaurants")
            }
        }
    }
}
