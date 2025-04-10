package com.example.quran.ui_composable

import androidx.compose.ui.text.style.TextAlign
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

        // Bismillah di tengah (selain surah 1 dan 9)
        if (surahNumber != 1 && surahNumber != 9) {
            item {
                Text(
                    text = "بِسْمِ اللَّهِ الرَّحْمَـٰنِ الرَّحِيمِ",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        items(ayahList) { ayah ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "${ayah.number}.",
                    style = MaterialTheme.typography.titleSmall
                )

                // Ayat Arab RTL + rata kanan
                Text(
                    text = ayah.arab,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )

                // Terjemahan Indonesia
                Text(
                    text = ayah.translation,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        }
    }
}