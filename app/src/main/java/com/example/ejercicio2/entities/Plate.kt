package com.example.ejercicio2.entities

data class Plate(
    val description: String,
    val hasFreeDelivery: Boolean,
    val ingredients: List<String>,
    val isFavourite: Boolean,
    val name: String,
    val photo: String,
    val price: Double,
    val rating: Double
)