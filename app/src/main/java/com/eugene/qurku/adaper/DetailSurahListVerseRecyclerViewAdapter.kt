package com.eugene.qurku.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugene.qurku.databinding.ComponentVerseBinding
import com.eugene.qurku.response.surahdetails.SurahDetailDataResponse
import com.eugene.qurku.response.surahdetails.SurahDetailVerseResponse

class DetailSurahListVerseRecyclerViewAdapter(
    private val context: Context,
    private val data: List<SurahDetailVerseResponse>,
    private val setOnClickPlayAudio: SetOnClickPlayAudio? = null,
    private val setOnClickInterpretation: SetOnClickInterpretation? = null
): RecyclerView.Adapter<DetailSurahListVerseRecyclerViewAdapter.ViewHolder>() {
    interface SetOnClickPlayAudio{
        fun onClickPlayAudio(position: Int)
    }

    interface SetOnClickInterpretation{
        fun onClickInterpretation(position: Int)
    }

    class ViewHolder(private val binding: ComponentVerseBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: SurahDetailVerseResponse,
            position: Int,
            setOnClickPlayAudio: SetOnClickPlayAudio? = null,
            setOnClickInterpretation: SetOnClickInterpretation? = null
        ){
            binding.number.text = data.nomorAyat.toString()
            binding.arabicText.text = data.teksArab
            binding.latinText.text = data.teksLatin
            binding.indonesianText.text = data.teksIndonesia
            binding.audioPlay.setOnClickListener { setOnClickPlayAudio?.onClickPlayAudio(position) }
            binding.interpretation.setOnClickListener { setOnClickInterpretation?.onClickInterpretation(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ComponentVerseBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position, setOnClickPlayAudio, setOnClickInterpretation)
    }
}