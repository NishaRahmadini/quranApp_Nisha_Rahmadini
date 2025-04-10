package com.example.quran.data

data class MultiEditionResponse(
    val data: List<EditionData>
)

data class EditionData(
    val edition: EditionInfo,
    val ayahs: List<Ayah>,
    val name: String,
    val englishName: String
)

data class EditionInfo(
    val identifier: String,
    val language: String,
    val name: String,
    val type: String
)

data class Ayah(
    val number: Int,
    val text: String,
    val numberInSurah: Int
)
