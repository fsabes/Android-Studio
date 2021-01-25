package com.example.ejercicio2.entities

data class User(var name : String = "", var surname :String = "",var  age :String =  "n/a") {

    fun getPlaceHolderUsers() : List<User>{
        return listOf(
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"),
                User("franco","sabes","26"))
    }
}