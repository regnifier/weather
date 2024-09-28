package com.example.weatherapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.presentation.detail.DetailScreen
import com.example.weatherapp.presentation.search.SearchScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { paddingValues ->
                        Navigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                    },
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "search_screen"
    ) {
        composable("search_screen") {
            SearchScreen(
                modifier = modifier,
                onNavigateToDetailScreen = { cityName, latitude, longitude ->
                    navController.navigate("detail_screen/$cityName/$latitude/$longitude")
                }
            )
        }

        composable("detail_screen/{name}/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("latitude") { type = NavType.FloatType },
                navArgument("longitude") { type = NavType.FloatType }
            )) { backStackEntry ->
            DetailScreen(
                modifier = modifier,
                cityName = backStackEntry.arguments?.getString("name")
                    ?: throw IllegalArgumentException("Name must be provided"),
                latitude = backStackEntry.arguments?.getFloat("latitude")
                    ?: throw IllegalArgumentException("Latitude must be provided"),
                longitude = backStackEntry.arguments?.getFloat("longitude")
                    ?: throw IllegalArgumentException("Longitude must be provided"),
            )
        }
    }
}