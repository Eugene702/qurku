package com.eugene.qurku.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugene.qurku.R
import com.eugene.qurku.databinding.ComponentSurahBinding
import com.eugene.qurku.response.listsurah.ListSurahDataResponse

class MainListSurahRecyclerViewAdapter(
    private val context: Context,
    private val data: List<ListSurahDataResponse>,
    private val setOnClickListener: ItemClickListener? = null
): RecyclerView.Adapter<MainListSurahRecyclerViewAdapter.ViewHolder>() {
    interface ItemClickListener{
        fun onItemClickListener(position: Int)
    }

    class ViewHolder(private val context: Context, private val binding: ComponentSurahBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: ListSurahDataResponse,
            position: Int,
            setOnClickListener: ItemClickListener? = null
        ){
            binding.name.text = data.nama
            binding.latinName.text = data.namaLatin
            "${data.jumlahAyat} ${context.getString(R.string.ayat)}".also { binding.verseCount.text = it }
            binding.disembarkation.text = data.tempatTurun
            binding.number.text = data.nomor.toString()
            "(${data.arti})".also { binding.nameMeaning.text = it }

            binding.layout.setOnClickListener {
                setOnClickListener?.onItemClickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, ComponentSurahBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position, setOnClickListener)
    }
}