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
import com.example.nutritionscanning.presenatation.streak.StreaksScreen

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
            DummyScreen("Upcoming")
        }

        composable<AppDestinations.LogsScreens> {
            NutritionResultsScreen()
        }

        composable<AppDestinations.StreaksScreens> {
            StreaksScreen()
        }

        composable<AppDestinations.ProfileScreens> {
            DummyScreen("Upcoming")
        }

        composable<AppDestinations.ScanScreens>(
            popExitTransition = { exitToBottom() },
            enterTransition = { enterFromBottom() },
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
                imageUri = Uri.parse(imageUri),
                onProcessCompleted = {
                    navController.popBackStack()
                    navController.navigate(AppDestinations.LogsScreens)
                }
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