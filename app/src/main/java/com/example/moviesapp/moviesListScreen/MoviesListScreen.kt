package com.example.moviesapp.moviesListScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.moviesListScreen.model.MovieResponse
import com.example.moviesapp.moviesListScreen.viewModel.MovieListViewModel
import com.example.moviesapp.views.DefaultToolbar


@Composable
fun MoviesListScreen(navigationCallback: () -> Unit) {

    //el viewmodel de esta manera vive, mientras el composable sigue vivo
    //no se reinicializa constantemente
    val viewmodel: MovieListViewModel = viewModel()
    val rememberdMovies: MutableState<List<MovieResponse>> =
        remember { mutableStateOf(emptyList<MovieResponse>()) }
    viewmodel.getMovies { response ->
        val moviesFromTheApi = response?.moviesList
        rememberdMovies.value = moviesFromTheApi.orEmpty()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultToolbar {

            }
        }
    ) {
        LazyColumn {
            items(rememberdMovies.value) { movie ->
                MovieItem(movie = movie, navigationCallback)
            }
        }
    }

}

@Composable
fun MovieItem(movie: MovieResponse, navigationCallback: () -> Unit) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { isExpanded = !isExpanded },
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.animateContentSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${movie.imageUrl}")
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier =
                if (isExpanded)
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxHeight()
                        .width(100.dp)
                        .padding(4.dp)
                        .align(Alignment.Top)
                else
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxHeight()
                        .width(100.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterVertically),
            )
            Column(
                modifier = Modifier
                    .weight(0.7F)
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                if (isExpanded) {
                    Text(
                        text = movie.desription,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    TextButton(
                        onClick =  {  },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .align(Alignment.Start)
                    ) {
                        Text(text = "See more")
                    }
                }
            }
            Icon(
                imageVector = if (isExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                modifier =
                if (isExpanded) {
                    Modifier
                        .weight(0.3F)
                        .align(Alignment.Bottom)
                        .padding(16.dp)
                } else
                    Modifier
                        .weight(0.3F)
                        .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun itemPreview(){
    val movie = MovieResponse(id = 1, "", title = "titulo falso", "descripcion falopa", date = "12/03/12", rating = 4.21f, "ingles")

    MovieItem(movie = movie, navigationCallback = {})
}

















