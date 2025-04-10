package com.example.quran.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.R
import com.example.quran.data.Ayah

class AyahAdapter(private val ayahList: List<Ayah>) :
    RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val ayah = ayahList[position]
        holder.bind(ayah)
    }

    override fun getItemCount(): Int = ayahList.size

    class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAyahText: TextView = itemView.findViewById(R.id.tvAyahText)
        private val tvAyahNumber: TextView = itemView.findViewById(R.id.tvAyahNumber)

        fun bind(ayah: Ayah) {
            tvAyahNumber.text = "${ayah.numberInSurah}."
            tvAyahText.text = ayah.text
        }
    }
}
