package com.example.quran.ui_composable

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.R
import com.example.quran.Retrofit_Instance.ApiClient
import com.example.quran.adapter.AyahAdapter
import com.example.quran.data.SurahDetailResponse
import com.example.quran.data.Ayah
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AyahAdapter

    private fun fetchSurahDetail(number: Int) {
        lifecycleScope.launch {
            try {
                val response = ApiClient.apiService.getSurahDetail(number)

                val arabAyahs =
                    response.data.find { it.edition.language == "ar" }?.ayahs ?: emptyList()
                val indoAyahs =
                    response.data.find { it.edition.language == "id" }?.ayahs ?: emptyList()

                val combined = arabAyahs.mapIndexed { index, arab ->
                    val indo = indoAyahs.getOrNull(index)
                    arab.copy(text = "${arab.text}\n${indo?.text.orEmpty()}")
                }

                adapter = AyahAdapter(combined)
                recyclerView.adapter = adapter

            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "Gagal ambil data", Toast.LENGTH_SHORT).show()
                Log.e("DetailActivity", "Error: ${e.message}")
            }
        }
    }
}