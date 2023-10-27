package com.example.moviesapp

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.movieDescriptionScreen.MovieDescriptionScreen
import com.example.moviesapp.movieLoginScreen.MovieLoginScreen
import com.example.moviesapp.moviesListScreen.MoviesListScreen
import com.example.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState =
                        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    Scaffold(
                        scaffoldState = scaffoldState,
                        content = {
                           // MovieLoginScreen()
                            MooviezApp()
                        // MovieDescriptionScreen()

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MooviezApp(){
    val navigationController = rememberNavController()
    NavHost(navigationController, startDestination = "destination_movies_list"){
        composable(route = "destination_movies_list"){

            MoviesListScreen(){
                navigationController.navigate("destination_movie_description")
            }
        }
        composable(
            route = "destination_movie_description",
            arguments = listOf(
                navArgument(name = "movie_description_id"){ type = NavType.IntType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movie_description_id")
            MovieDescriptionScreen()
        }
    }
}
