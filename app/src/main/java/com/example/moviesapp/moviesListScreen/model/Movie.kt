package com.example.moviesapp.moviesListScreen.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    @SerializedName("poster_path") val imageUrl: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val desription: String,
    @SerializedName("release_date") val date: String,
    @SerializedName("popularity") val rating: Float,
    @SerializedName("original_language") val language: String
)
