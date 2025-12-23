package com.example.foodiedelivery.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiedelivery.R

class CategoryAdapter(
    private val categories: List<String>,
    private val onCategoryClick: (String) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivCategoryIcon)
        val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.tvName.text = category

        val iconRes = when (category.lowercase()) {
            "all" -> R.drawable.ic_category_all
            "pizza" -> R.drawable.ic_category_pizza
            "burger" -> R.drawable.ic_category_burger
            "indian" -> R.drawable.ic_category_indian
            "chinese" -> R.drawable.ic_category_chinese
            "dessert" -> R.drawable.ic_category_dessert
            "drinks" -> R.drawable.ic_category_drinks
            else -> android.R.drawable.ic_menu_help
        }
        holder.ivIcon.setImageResource(iconRes)
        holder.itemView.setOnClickListener {
            onCategoryClick(category)
        }


    }

    override fun getItemCount() = categories.size
}
