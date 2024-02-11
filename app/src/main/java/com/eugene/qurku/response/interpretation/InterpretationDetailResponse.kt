package com.eugene.qurku.response.interpretation


import com.google.gson.annotations.SerializedName

data class InterpretationDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val interpretationDetailDataResponse: InterpretationDetailDataResponse,
    @SerializedName("message")
    val message: String
)