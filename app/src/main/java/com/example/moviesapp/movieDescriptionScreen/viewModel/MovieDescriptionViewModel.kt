package com.example.moviesapp.movieDescriptionScreen.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.movieDescriptionScreen.model.MovieDescription
import com.example.moviesapp.movieDescriptionScreen.model.MoviesDescriptionRepository

class MovieDescriptionViewModel(
    private val repository: MoviesDescriptionRepository = MoviesDescriptionRepository()) :
    ViewModel() {

    fun getMovies(successCallback: (response: MovieDescription?) -> Unit) {
        repository.getMoviesDescription { response ->
            successCallback(response)
        }
    }
}