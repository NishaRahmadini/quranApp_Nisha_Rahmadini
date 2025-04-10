package com.example.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.Retrofit_Instance.ApiClient
import com.example.quran.data.AyahPair
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _ayahList = MutableStateFlow<List<AyahPair>>(emptyList())
    val ayahList: StateFlow<List<AyahPair>> = _ayahList

    fun fetchSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getSurahDetail(surahNumber)

                val arabAyahs = response.data.find { it.edition.language == "ar" }?.ayahs ?: emptyList()
                val indoAyahs = response.data.find { it.edition.language == "id" }?.ayahs ?: emptyList()

                val combined = arabAyahs.mapIndexed { index, arabAyah ->
                    AyahPair(
                        number = arabAyah.numberInSurah,
                        arab = arabAyah.text,
                        translation = indoAyahs.getOrNull(index)?.text ?: ""
                    )
                }

                _ayahList.value = combined
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}