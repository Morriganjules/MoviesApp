package com.example.moviesapp.moviesListScreen.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.moviesListScreen.model.MoviesListResponse
import com.example.moviesapp.moviesListScreen.model.MoviesRepository

//prepara la informacion para ser desplegada

class MovieListViewModel(private val repository: MoviesRepository = MoviesRepository()) :
    ViewModel() {
    fun getMovies(successCallback: (response: MoviesListResponse?) -> Unit){
        repository.getMovies { response ->
            successCallback(response)
        }
    }
}