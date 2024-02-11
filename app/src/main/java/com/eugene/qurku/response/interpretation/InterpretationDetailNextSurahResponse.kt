package com.eugene.qurku.response.interpretation


import com.google.gson.annotations.SerializedName

data class InterpretationDetailNextSurahResponse(
    @SerializedName("jumlahAyat")
    val jumlahAyat: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("namaLatin")
    val namaLatin: String,
    @SerializedName("nomor")
    val nomor: Int
)