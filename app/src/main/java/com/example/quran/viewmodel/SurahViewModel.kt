package com.example.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.Retrofit_Instance.ApiClient
import com.example.quran.data.Surah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SurahViewModel : ViewModel() {

    private val _surahList = MutableStateFlow<List<Surah>>(emptyList())
    val surahList: StateFlow<List<Surah>> = _surahList

    init {
        fetchSurah()
    }

    private fun fetchSurah() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getAllSurah()
                _surahList.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
