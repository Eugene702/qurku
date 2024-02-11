package com.eugene.qurku.response.listsurah


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListSurahAudioFullResponse(
    @SerializedName("01")
    val x01: String,
    @SerializedName("02")
    val x02: String,
    @SerializedName("03")
    val x03: String,
    @SerializedName("04")
    val x04: String,
    @SerializedName("05")
    val x05: String
): Parcelable