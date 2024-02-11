package com.eugene.qurku.repository

import com.eugene.qurku.api.ApiClient
import com.eugene.qurku.response.listsurah.ListSurahResponse
import retrofit2.Response

class MainRepository {
    suspend fun getAllSurah(): Response<ListSurahResponse>{
        return ApiClient.eQuranApi.getAllSurah()
    }
}