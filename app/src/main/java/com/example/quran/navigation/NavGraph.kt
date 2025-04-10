package com.example.quran.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quran.ui_composable.SurahListScreen
import com.example.quran.ui_composable.DetailSurahScreen

@Composable
fun QuranNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "surah_list"
    ) {
        composable("surah_list") {
            SurahListScreen(navController = navController)
        }
        composable("detail/{number}") { backStackEntry ->
            val surahNumber = backStackEntry.arguments?.getString("number")?.toIntOrNull() ?: 1
            DetailSurahScreen(surahNumber = surahNumber)
        }
    }
}
