package com.example.moviesapp.moviesListScreen.model.api

import com.example.moviesapp.movieDescriptionScreen.model.MovieDescription
import com.example.moviesapp.moviesListScreen.model.MoviesListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MoviesWebService {
    private lateinit var api: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MoviesApi::class.java)
    }
    fun getMovies(): Call<MoviesListResponse>{
       return api.getMovies()
    }

    fun getMoviesDescription(movieId: Int = 200): Call<MovieDescription>{
        return api.getMoviesDescription(movieId)
    }

    interface MoviesApi {
        @GET("movie/popular")
        fun getMovies(
            @Query("api_key") apiKey: String = "2f2c5419a59e275f372ef56e8f0ff35b",
            @Query("language") language: String = "en-US",
            @Query("page") page: String = "1"
        ) : Call<MoviesListResponse>

        @GET("movie/{movie_id}")
        fun getMoviesDescription(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = "2f2c5419a59e275f372ef56e8f0ff35b",
            @Query("language") language: String = "en-US"
        ) : Call<MovieDescription>
    }
}