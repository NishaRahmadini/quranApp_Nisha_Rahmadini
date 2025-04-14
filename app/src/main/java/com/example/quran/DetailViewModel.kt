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

    private fun convertToArabicNumber(number: Int): String {
        val arabicNumbers = listOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
        return number.toString().map { arabicNumbers[it.digitToInt()] }.joinToString("")
    }

    fun fetchSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getSurahDetail(surahNumber)

                val arabAyahs =
                    response.data.find { it.edition.language == "ar" }?.ayahs ?: emptyList()
                val indoAyahs =
                    response.data.find { it.edition.language == "id" }?.ayahs ?: emptyList()

                val combined = arabAyahs.mapIndexed { index, arabAyah ->
                    val indo = indoAyahs.getOrNull(index)
                    val arabNumber = convertToArabicNumber(arabAyah.numberInSurah)

                    AyahPair(
                        number = arabAyah.numberInSurah,
                        arab = "﴾${arabAyah.text} ﴿$arabNumber", // tampilkan angka arab di akhir ayat
                        translation = indo?.text ?: ""
                    )
                }

                _ayahList.value = combined
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
