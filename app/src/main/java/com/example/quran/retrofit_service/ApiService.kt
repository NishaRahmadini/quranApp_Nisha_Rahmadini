package com.example.quran.retrofit_service

import com.example.quran.data.MultiEditionResponse
import com.example.quran.data.SurahResponse
import com.example.quran.data.SurahDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Endpoint untuk menampilkan daftar surah
    @GET("surah")
    suspend fun getAllSurah(): SurahResponse

    @GET("surah/{number}/editions/quran-simple,id.indonesian")
    suspend fun getSurahDetail(@Path("number") number: Int): MultiEditionResponse

}