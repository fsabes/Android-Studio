package com.example.ejercicio2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.entities.recipes.Recipes
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.RoundedCornerTreatment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvTitle = view.findViewById<TextView>(R.id.item_title)
        var tvSurname = view.findViewById<TextView>(R.id.item_subtitle)
        var tvAge = view.findViewById<TextView>(R.id.item_price)
        var ivImage = view.findViewById<ImageView>(R.id.item_image_view)

        fun onBind(recipe: Recipe){
            tvTitle.text = recipe.title
            tvSurname.text = recipe.sourceName
            tvAge.text = recipe.title

            Picasso.get()
                    .load(recipe.image)
                    .into(ivImage)
        }
    }

    companion object{
        fun setRecipeAdapter(context : Context ,rvRecipes: RecyclerView, listRecipes: Recipes) {
            rvRecipes.adapter = RecipeAdapter(listRecipes)
            rvRecipes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}

