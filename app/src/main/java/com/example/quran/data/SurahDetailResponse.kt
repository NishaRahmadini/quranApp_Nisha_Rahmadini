package com.example.quran.data

data class SurahDetailResponse(
    val data: SurahData
)

data class SurahData(
    val name: String,
    val englishName: String,
    val number: Int,
    val ayahs: List<Ayah>
)

