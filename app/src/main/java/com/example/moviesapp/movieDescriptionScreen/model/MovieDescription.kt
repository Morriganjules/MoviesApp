package com.example.moviesapp.movieDescriptionScreen.model

import com.google.gson.annotations.SerializedName

data class MovieDescription(
    @SerializedName("backdrop_path") var imageUrl: String = "",
    @SerializedName("budget") var budget: Double = 0.0,
    @SerializedName("genres") var genres: Array<Genres>? = null,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("original_language") var language: String = "",
    @SerializedName("original_title") var title: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("overview") var description: String = "",
    @SerializedName("vote_average") var voteAverage: Float = 0F
)

data class Genres (
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
        )