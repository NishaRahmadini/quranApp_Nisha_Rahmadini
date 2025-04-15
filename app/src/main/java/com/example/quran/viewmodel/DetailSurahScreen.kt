package com.example.quran.viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quran.viewmodel.DetailSurahViewModel
import android.media.MediaPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DetailSurahScreen(surahNumber: Int) {
    val viewModel: DetailSurahViewModel = viewModel()
    val ayahList by viewModel.ayahList.collectAsState()
    val surahName by viewModel.surahName.collectAsState()



    LaunchedEffect(surahNumber) {
        viewModel.fetchSurahDetail(surahNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = surahName) })
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(ayahList) { ayah ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "${ayah.numberInSurah}. ${ayah.text}")
                }
            }
        }
    }
}
