package com.navigation.live.presentation.ui.shared.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.navigation.live.presentation.ui.add_note.view.AddNotesScreen
import com.navigation.live.presentation.ui.all_notes.view.HomeScreen

sealed class Screen(
    val route: String
) {
    object Home : Screen(route = "Home")
    object AddNotes : Screen(route = "add_edit_notes") {
        fun createRoute(noteId: Int? = null, noteColor: Int? = null): String {
            return if (noteId != null && noteColor != null) {
                "add_edit_notes?noteId=$noteId&noteColor=$noteColor"
            } else {
                "add_edit_notes"
            }
        }

    }
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNoteClick = { note ->
                    navHostController.navigate(
                        Screen.AddNotes.createRoute(
                            noteId = note.id,
                            noteColor = note.color
                        )
                    )
                },
                addNotesClick = {
                    navHostController.navigate(Screen.AddNotes.route)
                }

            )
        }

        composable(Screen.AddNotes.route) {
            AddNotesScreen(
                onNavigationBack = {
                    navHostController.popBackStack()
                }
            )
        }

        //Route for editing existing note (with argument)
        composable(
            route = "${Screen.AddNotes.route}?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                },
                navArgument("noteColor") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            val noteColor = backStackEntry.arguments?.getInt("noteColor")
            AddNotesScreen(
                onNavigationBack = {
                    navHostController.popBackStack()
                }
            )

        }


    }

}