package com.eugene.qurku.response.interpretation


import com.google.gson.annotations.SerializedName

data class InterpretationDetailInterpretationResponse(
    @SerializedName("ayat")
    val ayat: Int,
    @SerializedName("teks")
    val teks: String
)