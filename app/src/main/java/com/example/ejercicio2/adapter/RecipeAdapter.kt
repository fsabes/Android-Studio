package com.example.ejercicio2.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.R
import com.example.ejercicio2.app.AppConstants
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.entities.recipes.Recipes
import com.example.ejercicio2.ui.details.DetailsActivity
import com.squareup.picasso.Picasso

class RecipeAdapter(var recipes: Recipes) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(recipes.recipes[position])
    }

    override fun getItemCount(): Int = recipes.recipes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById<TextView>(R.id.item_title)
        var tvSurname: TextView = view.findViewById<TextView>(R.id.item_subtitle)
        var tvPrice: TextView = view.findViewById<TextView>(R.id.item_price)
        var ivImage: ImageView = view.findViewById<ImageView>(R.id.item_image_view)
        var tvGlutenFree: TextView = view.findViewById<TextView>(R.id.tv_item_gluten_free)

        fun onBind(recipe: Recipe) {
            tvTitle.text = recipe.title
            tvSurname.text = recipe.summary
            tvPrice.text = "$${recipe.pricePerServing}"
            if (recipe.glutenFree) {
                tvGlutenFree.text = AppConstants.GLUTEN_FREE
            }

            Picasso.get()
                .load(recipe.image)
                .into(ivImage)

            itemView.findViewById<Button>(R.id.btn_ver_detalles).setOnClickListener {
                val intent = Intent(it?.context, DetailsActivity::class.java)
                intent.putExtra(AppConstants.ID, recipe.id)
                startActivity(it.context, intent, Bundle())
            }

        }
    }

    companion object {
        fun setRecipeAdapter(context: Context, rvRecipes: RecyclerView, listRecipes: Recipes) {
            rvRecipes.adapter = RecipeAdapter(listRecipes)
            rvRecipes.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}

