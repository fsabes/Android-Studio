package com.example.ejercicio2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.entities.recipes.Recipes
import com.squareup.picasso.Picasso

class RecipeVerticalAdapter(var recipes: Recipes, var onClickRecipe: OnClickRecipe) :
    RecyclerView.Adapter<RecipeVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(recipes.recipes[position])
    }

    override fun getItemCount(): Int = recipes.recipes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById<TextView>(R.id.tv_item_Vertical_title)
        var tvPrice: TextView = view.findViewById<TextView>(R.id.tv_item_Vertical_price)
        var tvDelivery: TextView = view.findViewById<TextView>(R.id.tv_item_Vertical_delivery)
        var ivImage: ImageView = view.findViewById<ImageView>(R.id.iv_item_vertical)
        var tvGlutenFree: TextView = view.findViewById<TextView>(R.id.tv_item_vertical_gluten_free)

        fun onBind(recipe: Recipe) {
            tvTitle.text = recipe.title
            tvPrice.text = "$${recipe.pricePerServing}"
            tvDelivery.text = AppConstants.DELIVERY_FREE
            if (recipe.glutenFree) {
                tvGlutenFree.text = AppConstants.GLUTEN_FREE
            }

            Picasso.get()
                .load(recipe.image)
                .into(ivImage)

            itemView.setOnClickListener {
                onClickRecipe.onClickRecipe(recipe.id)
            }
        }
    }

    interface OnClickRecipe {
        fun onClickRecipe(id: Int)
    }

    companion object {
        fun setRecipeVerticalAdapter(
            context: Context,
            rvRecipes: RecyclerView,
            listRecipes: Recipes,
            onClickRecipe: OnClickRecipe
        ) {
            rvRecipes.adapter = RecipeVerticalAdapter(listRecipes, onClickRecipe)
            rvRecipes.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}