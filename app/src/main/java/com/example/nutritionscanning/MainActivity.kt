package com.example.nutritionscanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material.Scaffold
import androidx.compose.material.FabPosition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutritionscanning.presenatation.core.AppBottomNavigation
import com.example.nutritionscanning.presenatation.core.AppDestinations
import com.example.nutritionscanning.presenatation.core.BottomScreens
import com.example.nutritionscanning.presenatation.core.RootNavigation
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.NutritionScanningTheme
import com.example.nutritionscanning.ui.theme.White
import com.example.nutritionscanning.ui.theme.Yellow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutritionScanningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    val navController = rememberNavController()

                    val bottomScreens = remember {
                        listOf(
                            BottomScreens.Home, BottomScreens.Logs, BottomScreens.Scan,
                            BottomScreens.Streaks, BottomScreens.Profile
                        )
                    }

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Scaffold(
                        bottomBar = {
                            AppBottomNavigation(
                                navController = navController,
                                currentDestination = currentDestination,
                                bottomScreens = bottomScreens,
                            )
                        },
                        floatingActionButton = {
                            Box {
                                FloatingActionButton(
                                    onClick = {
                                        navController.navigate(AppDestinations.ScanScreens)
                                    },
                                    containerColor = Yellow,
                                    contentColor = Black,
                                    modifier = Modifier
                                        .size(54.dp)
                                        .padding(4.dp)
                                        .align(Alignment.BottomCenter)
                                        .offset(y = (30).dp) // Raise the FAB to overlap the BottomNavigation
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_home),
                                        contentDescription = "Scan",
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true
                    ) { innerPadding ->
                        Box {
                            RootNavigation(
                                modifier = Modifier.padding(innerPadding),
                                startDestination = AppDestinations.HomeScreens,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}