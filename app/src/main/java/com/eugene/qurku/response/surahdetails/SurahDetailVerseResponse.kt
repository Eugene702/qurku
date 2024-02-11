package com.eugene.qurku.response.surahdetails


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurahDetailVerseResponse(
    @SerializedName("audio")
    val surahDetailAudioResponse: SurahDetailAudioResponse,
    @SerializedName("nomorAyat")
    val nomorAyat: Int,
    @SerializedName("teksArab")
    val teksArab: String,
    @SerializedName("teksIndonesia")
    val teksIndonesia: String,
    @SerializedName("teksLatin")
    val teksLatin: String
): Parcelable