package com.example.moviesapp.movieDescriptionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.movieDescriptionScreen.model.MovieDescription
import com.example.moviesapp.movieDescriptionScreen.viewModel.MovieDescriptionViewModel

@Composable
fun MovieDescriptionScreen() {
    val viewmodel: MovieDescriptionViewModel = viewModel()
    val description: MutableState<MovieDescription> = remember {
        mutableStateOf(MovieDescription())
    }
    viewmodel.getMovies { response ->
        if (response != null) {
            description.value = response
        }
    }
    MovieDescriptionItem(movie = description.value)
}

@Composable
fun MovieDescriptionItem(movie: MovieDescription) {
    Surface(
    ) {
        Column() {

            Column() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${movie.imageUrl}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .padding(6.dp),
                    elevation = 8.dp,
                ) {
                    Row(
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.6f)
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.h5,
                                maxLines = 2,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 6.dp),
                            )
                            Text(
                                text = movie.releaseDate,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.caption,
                            )
                            Text(
                                text = movie.genres?.get(0)?.name.toString(),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.caption,
                            )
                            Text(
                                text = movie.language,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 2.dp,
                                    bottom = 6.dp
                                ),
                                style = MaterialTheme.typography.caption,
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = if (movie.voteAverage < 4f) {
                                    badReview()
                                } else if (movie.voteAverage > 8f) {
                                    goodReview()
                                } else {
                                    regularReview()
                                },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = movie.voteAverage.toString(),
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Text(
                                text = "rating bar",

                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(6.dp),
                elevation = 8.dp,
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = movie.description,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icono(
                            resource = R.drawable.add_vector,
                            text = "Favoritos",
                            onClick = {}
                        )
                        Icono(
                            resource = R.drawable.movie_vector,
                            text = "Ver trailer",
                            onClick = {}
                        )
                        Icono(
                            resource = R.drawable.share_vector,
                            text = "Compartir",
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

fun badReview() = Modifier
    .clip(CircleShape)
    .background(Color.Red)
    .size(100.dp)

fun regularReview() = Modifier
    .clip(CircleShape)
    .background(Color.Yellow)
    .size(100.dp)

fun goodReview() = Modifier
    .clip(CircleShape)
    .background(Color.Green)
    .size(100.dp)

@Composable
fun Icono(text: String, resource: Int, onClick: () -> Unit?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )
        Icon(
            painter = painterResource(id = resource),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clickable
                {
                    onClick
                },
            tint = MaterialTheme.colors.primary,

            )
    }
}