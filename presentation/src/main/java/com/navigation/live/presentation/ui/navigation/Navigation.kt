package com.navigation.live.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.navigation.live.presentation.ui.screen.AddNotesScreen
import com.navigation.live.presentation.ui.screen.HomeScreen

sealed class Screen(
    val route: String
) {
    object Home : Screen(route = "Home")
    object AddNotes : Screen(route = "Add Notes")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen {
                navHostController.navigate(Screen.AddNotes.route)
            }
        }

        composable(Screen.AddNotes.route) {
            AddNotesScreen(
                onNavigationBack = {
                    navHostController.popBackStack()
                }
            )
        }

    }

}