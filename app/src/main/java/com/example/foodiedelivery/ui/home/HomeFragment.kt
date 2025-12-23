package com.example.foodiedelivery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiedelivery.R
import com.example.foodiedelivery.data.model.Restaurant
import com.google.android.material.textfield.TextInputEditText
import androidx.core.widget.addTextChangedListener

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvRestaurants: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvErrorMessage: TextView
    private lateinit var restaurantAdapter: RestaurantAdapter
    private var fullRestaurantList: List<Restaurant> = emptyList()
    private lateinit var etSearch: TextInputEditText



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCategories = view.findViewById(R.id.rvCategories)
        rvRestaurants = view.findViewById(R.id.rvRestaurants)
        progressBar = view.findViewById(R.id.progressBar)
        tvErrorMessage = view.findViewById(R.id.tvErrorMessage)
        etSearch = view.findViewById(R.id.etSearch)



        // Categories static
        val categories = listOf("All", "Pizza", "Burger", "Indian", "Chinese", "Dessert", "Drinks")

        rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvCategories.adapter = CategoryAdapter(categories) { selected ->
            val filtered = if (selected.equals("All", ignoreCase = true)) {
                fullRestaurantList
            } else {
                fullRestaurantList.filter { restaurant ->
                    restaurant.cuisine.contains(selected, ignoreCase = true) ||
                            restaurant.name.contains(selected, ignoreCase = true)
                }
            }
            restaurantAdapter.updateData(filtered)
            tvErrorMessage.visibility =
                if (filtered.isEmpty()) View.VISIBLE else View.GONE
            tvErrorMessage.text =
                if (filtered.isEmpty()) "No restaurants found" else ""
        }


        rvRestaurants.layoutManager = LinearLayoutManager(requireContext())
        restaurantAdapter = RestaurantAdapter(emptyList())
        rvRestaurants.adapter = restaurantAdapter


        // Observe ViewModel
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeUiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    tvErrorMessage.visibility = View.GONE
                    rvRestaurants.visibility = View.GONE
                }
                is HomeUiState.Success -> {
                    progressBar.visibility = View.GONE
                    tvErrorMessage.visibility = View.GONE
                    rvRestaurants.visibility = View.VISIBLE

                    fullRestaurantList = state.restaurants
                    restaurantAdapter.updateData(fullRestaurantList)
                }

                is HomeUiState.Error -> {
                    progressBar.visibility = View.GONE
                    rvRestaurants.visibility = View.GONE
                    tvErrorMessage.visibility = View.VISIBLE
                    tvErrorMessage.text = state.message
                }
            }
        }
        etSearch.addTextChangedListener { text ->
            val query = text?.toString()?.trim().orEmpty().lowercase()

            val filtered = if (query.isEmpty()) {
                fullRestaurantList
            } else {
                fullRestaurantList.filter { restaurant ->
                    restaurant.name.lowercase().contains(query) ||
                            restaurant.cuisine.lowercase().contains(query)
                }
            }
            restaurantAdapter.updateData(filtered)

            tvErrorMessage.visibility =
                if (filtered.isEmpty()) View.VISIBLE else View.GONE
            tvErrorMessage.text =
                if (filtered.isEmpty()) "No restaurants found" else ""
        }

    }
}
