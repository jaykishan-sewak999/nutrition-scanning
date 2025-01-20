package com.example.nutritionscanning.presenatation.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nutritionscanning.ui.theme.Black
import com.example.nutritionscanning.ui.theme.Gray
import com.example.nutritionscanning.ui.theme.White

@Composable
fun AppBottomNavigation(
    navController: NavController,
    currentDestination: NavDestination?,
    isBelongToBottomScreens: Boolean,
    bottomScreens: List<BottomScreens<out AppDestinations>>,
) {

    if (isBelongToBottomScreens) {
        BottomAppBar(
            modifier = Modifier
                .navigationBarsPadding(),
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
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
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
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Black else Gray
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
}