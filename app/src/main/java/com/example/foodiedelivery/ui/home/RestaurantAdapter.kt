package com.example.foodiedelivery.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodiedelivery.R
import com.example.foodiedelivery.data.model.Restaurant

class RestaurantAdapter(private var restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivRestaurantImage)
        val tvName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        val tvCuisine: TextView = itemView.findViewById(R.id.tvCuisine)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val tvTimePrice: TextView = itemView.findViewById(R.id.tvTimeAndPrice)
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadge)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.tvName.text = restaurant.name
        holder.tvCuisine.text = restaurant.cuisine
        holder.tvRating.text = "★ ${restaurant.rating}"
        holder.tvTimePrice.text = restaurant.deliveryTimeText


        Glide.with(holder.itemView.context)
            .load(restaurant.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .into(holder.ivImage)

        holder.tvBadge.visibility =
            if (restaurant.isFeatured) View.VISIBLE else View.GONE
    }


    override fun getItemCount() = restaurants.size

    fun updateData(newList: List<Restaurant>) {
        restaurants = newList
        notifyDataSetChanged()
    }
}
