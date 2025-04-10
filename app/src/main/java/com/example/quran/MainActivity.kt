package com.example.quran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quran.ui.theme.QuranTheme
import com.example.quran.ui_composable.SurahListScreen
import com.example.quran.ui_composable.DetailSurahScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            SurahListScreen(navController = navController)
                        }
                        composable("detail/{surahNumber}") { backStackEntry ->
                            val surahNumber = backStackEntry.arguments?.getString("surahNumber")?.toIntOrNull() ?: 1
                            DetailSurahScreen(surahNumber = surahNumber)
                        }
                    }
                }
            }
        }
    }
}
