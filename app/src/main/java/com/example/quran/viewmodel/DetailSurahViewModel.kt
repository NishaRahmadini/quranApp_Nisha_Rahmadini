package com.example.quran.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.Retrofit_Instance.ApiClient
import com.example.quran.data.Ayah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailSurahViewModel : ViewModel() {

    private val _ayahList = MutableStateFlow<List<Ayah>>(emptyList())
    val ayahList: StateFlow<List<Ayah>> = _ayahList

    private val _surahName = MutableStateFlow("")
    val surahName: StateFlow<String> = _surahName

    fun fetchSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getSurahDetail(surahNumber)

                val arabAyahs =
                    response.data.find { it.edition.language == "ar" }?.ayahs ?: emptyList()
                val indoAyahs =
                    response.data.find { it.edition.language == "id" }?.ayahs ?: emptyList()

                val combined = arabAyahs.mapIndexed { index, arab ->
                    val indo = indoAyahs.getOrNull(index)
                    arab.copy(text = "${arab.text}\n${indo?.text.orEmpty()}")
                }

                _ayahList.value = combined

                // ambil nama surah dari salah satu edisi
                val name = response.data.firstOrNull()?.name ?: ""
                _surahName.value = name

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}