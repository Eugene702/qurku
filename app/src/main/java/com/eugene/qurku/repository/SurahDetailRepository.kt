package com.eugene.qurku.repository

import com.eugene.qurku.api.ApiClient
import com.eugene.qurku.response.interpretation.InterpretationDetailResponse
import com.eugene.qurku.response.surahdetails.SurahDetailResponse
import retrofit2.Response

class SurahDetailRepository {
    suspend fun getSurahDetail(number: Int): Response<SurahDetailResponse>{
        return ApiClient.eQuranApi.getSurahDetails(number)
    }

    suspend fun getInterpretationDetail(number: Int): Response<InterpretationDetailResponse>{
        return ApiClient.eQuranApi.getInterpretationDetails(number)
    }
}