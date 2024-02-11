package com.eugene.qurku.response.interpretation


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class InterpretationDetailDataResponse(
    @SerializedName("arti")
    val arti: String,
    @SerializedName("audioFull")
    val interpretationDetailAudioFullResponse: InterpretationDetailAudioFullResponse,
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
    val interpretationDetailPreviousSurahResponse: @RawValue Any?,
    @SerializedName("suratSelanjutnya")
    val interpretationDetailNextSurahResponse: @RawValue Any?,
    @SerializedName("tafsir")
    val interpretationDetailInterpretationResponse: List<InterpretationDetailInterpretationResponse>,
    @SerializedName("tempatTurun")
    val tempatTurun: String
)