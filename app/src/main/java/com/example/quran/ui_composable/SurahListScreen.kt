package com.example.quran.ui_composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.quran.viewmodel.SurahViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahListScreen(viewModel: SurahViewModel = viewModel(), navController: NavHostController) {
    val surahList by viewModel.surahList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Al-Qur'an") }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(surahList) { surah ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("detail/${surah.number}") // ⬅️ navigasi ke detail
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${surah.number}. ${surah.englishName}", style = MaterialTheme.typography.titleMedium)
                        Text(text = surah.name, style = MaterialTheme.typography.titleSmall)
                        Text(text = "${surah.revelationType} - ${surah.numberOfAyahs} ayat")
                    }
                }
            }
        }
    }
}