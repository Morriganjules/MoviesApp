package com.example.moviesapp.moviesListScreen.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page") val pages : Int,
    @SerializedName("results") val moviesList: List<MovieResponse>
)
