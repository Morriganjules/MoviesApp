package com.example.moviesapp.moviesListScreen.model

import com.example.moviesapp.moviesListScreen.model.api.MoviesWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Repository obtiene la data del server

class MoviesRepository(private val webService: MoviesWebService = MoviesWebService()) {
    fun getMovies(successCallback: (response: MoviesListResponse?) -> Unit) {
        return webService.getMovies().enqueue(object : Callback<MoviesListResponse> {
            override fun onResponse(
                call: Call<MoviesListResponse>,
                response: Response<MoviesListResponse>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}