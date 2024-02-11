package com.eugene.qurku.response.listsurah


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListSurahResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<ListSurahDataResponse>,
    @SerializedName("message")
    val message: String
): Parcelable