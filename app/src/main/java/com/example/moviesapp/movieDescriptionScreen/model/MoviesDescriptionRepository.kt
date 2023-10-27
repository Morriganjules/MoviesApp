package com.example.moviesapp.movieDescriptionScreen.model

import android.util.Log
import com.example.moviesapp.moviesListScreen.model.api.MoviesWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDescriptionRepository(private val webService: MoviesWebService = MoviesWebService()) {
    fun getMoviesDescription(successCallback: (response: MovieDescription?) -> Unit) {
        return webService.getMoviesDescription().enqueue(object : Callback<MovieDescription> {
            override fun onResponse(
                call: Call<MovieDescription>,
                response: Response<MovieDescription>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MovieDescription>, t: Throwable) {
                Log.e("moviesDescription", "algo salio mal", t)
            }
        })
    }
}