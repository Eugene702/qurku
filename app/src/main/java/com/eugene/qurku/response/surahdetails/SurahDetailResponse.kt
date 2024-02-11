package com.eugene.qurku.response.surahdetails


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurahDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val surahDetailDataResponse: SurahDetailDataResponse,
    @SerializedName("message")
    val message: String
): Parcelable