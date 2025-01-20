package com.example.nutritionscanning.presenatation.core

import kotlinx.serialization.Serializable
import com.example.nutritionscanning.R

sealed class AppDestinations {
    @Serializable
    data object HomeScreens : AppDestinations()

    @Serializable
    data object LogsScreens : AppDestinations()

    @Serializable
    data object StreaksScreens : AppDestinations()

    @Serializable
    data object ProfileScreens : AppDestinations()

    @Serializable
    data object ScanScreens : AppDestinations()

    @Serializable
    data class ImageProcessingScreens(val imageUri: String) : AppDestinations()
}

@Serializable
sealed class BottomScreens<T>(val name: String, val icon: Int, val route: T) {
    @Serializable
    data object Home : BottomScreens<AppDestinations.HomeScreens>(
        name = "Home",
        icon = R.drawable.outline_home,
        route = AppDestinations.HomeScreens
    )

    @Serializable
    data object Logs : BottomScreens<AppDestinations.LogsScreens>(
        name = "Logs",
        icon = R.drawable.outline_logs,
        route = AppDestinations.LogsScreens
    )


    data object Scan : BottomScreens<AppDestinations.ScanScreens>(
        name = "Scan",
        icon = R.drawable.outline_home,
        route = AppDestinations.ScanScreens
    )

    @Serializable
    data object Streaks : BottomScreens<AppDestinations.StreaksScreens>(
        name = "Streaks",
        icon = R.drawable.outline_fire,
        route = AppDestinations.StreaksScreens
    )

    @Serializable
    data object Profile : BottomScreens<AppDestinations.ProfileScreens>(
        name = "Profile",
        icon = R.drawable.outline_person,
        route = AppDestinations.ProfileScreens
    )
}