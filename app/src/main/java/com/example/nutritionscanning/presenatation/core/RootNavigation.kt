package com.example.nutritionscanning.presenatation.core

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.nutritionscanning.presenatation.imageprocessing.ImageProcessingScreen
import com.example.nutritionscanning.presenatation.nutritionresult.NutritionResultsScreen
import com.example.nutritionscanning.presenatation.scanning.screen.ScanningScreen

@Composable
fun RootNavigation(
    modifier: Modifier,
    startDestination: AppDestinations,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<AppDestinations.HomeScreens> {
            NutritionResultsScreen()
        }

        composable<AppDestinations.LogsScreens> {
            DummyScreen("Logs")
        }

        composable<AppDestinations.StreaksScreens> {
            DummyScreen("Streaks")
        }

        composable<AppDestinations.ProfileScreens> {
            DummyScreen("Profile")
        }

        composable<AppDestinations.ScanScreens>(
            popExitTransition = { exitToBottom() },
            enterTransition = { enterFromLeft() },
            exitTransition = { exitToLeft() }
        ) {
            ScanningScreen(
                onNavBack = {
                    navController.navigateUp()
                },
                onNavigateToImageProcessing = {
                    navController.popBackStack()
                    navController.navigate(AppDestinations.ImageProcessingScreens(it.toString()))
                }
            )
        }

        composable<AppDestinations.ImageProcessingScreens>(
            popExitTransition = { exitToRight() },
            enterTransition = { enterFromRight() }
        ) {
            val imageUri = it.toRoute<AppDestinations.ImageProcessingScreens>().imageUri
            ImageProcessingScreen(
                imageUri = Uri.parse(imageUri)
            )
        }
    }
}

@Composable
fun DummyScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(title, Modifier.align(Alignment.Center))
    }
}