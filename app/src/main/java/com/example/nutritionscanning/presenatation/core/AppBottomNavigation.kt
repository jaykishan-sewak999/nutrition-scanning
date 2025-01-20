package com.example.nutritionscanning.presenatation.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nutritionscanning.ui.theme.Gray
import com.example.nutritionscanning.ui.theme.White

@Composable
fun AppBottomNavigation(
    navController: NavController,
    currentDestination: NavDestination?,
    bottomScreens: List<BottomScreens<out AppDestinations>>,
) {
    BottomAppBar(
        modifier = Modifier
            .height(76.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        backgroundColor = White
    ) {
        bottomScreens.forEachIndexed { index, screen ->
            val isSelected = currentDestination
                ?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true
            if (index != 2) {
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(screen.icon),
                            contentDescription = screen.name
                        )
                    },
                    label = {
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = screen.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Gray
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                )
            } else {
                NavigationBarItem(
                    icon = {},
                    label = { },
                    selected = false,
                    onClick = { },
                    enabled = false
                )
            }
        }
    }
}