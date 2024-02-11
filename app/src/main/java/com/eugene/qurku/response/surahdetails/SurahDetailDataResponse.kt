package com.eugene.qurku.response.surahdetails


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SurahDetailDataResponse(
    @SerializedName("arti")
    val arti: String,
    @SerializedName("audioFull")
    val surahDetailAudioFullResponse: SurahDetailAudioFullResponse,
    @SerializedName("ayat")
    val surahDetailVerseResponse: List<SurahDetailVerseResponse>,
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
    @SerializedName("suratSebelumnya")
    val surahDetailPreviousResponse: @RawValue Any?,
    @SerializedName("suratSelanjutnya")
    val surahDetailNextResponse: @RawValue Any?,
    @SerializedName("tempatTurun")
    val tempatTurun: String
): Parcelable