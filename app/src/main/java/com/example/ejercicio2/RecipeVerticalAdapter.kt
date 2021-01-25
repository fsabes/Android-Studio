package com.example.ejercicio2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.entities.recipes.Recipe
import com.example.ejercicio2.entities.recipes.Recipes
import com.squareup.picasso.Picasso

class RecipeVerticalAdapter(var recipes: Recipes) : RecyclerView.Adapter<RecipeVerticalAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvTitle = view.findViewById<TextView>(R.id.tv_item_Vertical_title)
        var tvPrice = view.findViewById<TextView>(R.id.tv_item_Vertical_price)
        var tvDelivery = view.findViewById<TextView>(R.id.tv_item_Vertical_delivery)
        var ivImage = view.findViewById<ImageView>(R.id.iv_item_vertical)
        var tvAptoCeliaco =  view.findViewById<TextView>(R.id.tv_item_vertical_apto_celiaco)

        fun onBind(recipe: Recipe){
            tvTitle.text = recipe.title
            tvPrice.text = recipe.id.toString()
            tvDelivery.text = "Delivery Gratis"
            if(recipe.glutenFree){
                tvAptoCeliaco.text = "GlutenFree"
            }else{
                tvAptoCeliaco.text = ""
            }
            Picasso.get()
                .load(recipe.image)
                .into(ivImage)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(recipes.recipes[position])
    }

    override fun getItemCount(): Int = recipes.recipes.size

}