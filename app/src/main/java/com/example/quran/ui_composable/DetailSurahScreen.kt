package com.example.quran.ui_composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quran.viewmodel.DetailViewModel

@Composable
fun DetailSurahScreen(
    surahNumber: Int,
    viewModel: DetailViewModel = viewModel()
) {
    val ayahList by viewModel.ayahList.collectAsState()

    LaunchedEffect(surahNumber) {
        viewModel.fetchSurahDetail(surahNumber)
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(ayahList) { ayah ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "${ayah.numberInSurah}.",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ayah.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
