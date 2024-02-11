package com.eugene.qurku.response.listsurah


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListSurahDataResponse(
    @SerializedName("arti")
    val arti: String,
    @SerializedName("audioFull")
    val listSurahAudioFullResponse: ListSurahAudioFullResponse,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("jumlahAyat")
    val jumlahAyat: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaLatin")
    val namaLatin: String,
    @SerializedName("nomor")
    val nomor: Int,
    @SerializedName("tempatTurun")
    val tempatTurun: String
): Parcelable