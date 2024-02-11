package com.eugene.qurku.api

import com.eugene.qurku.response.interpretation.InterpretationDetailResponse
import com.eugene.qurku.response.listsurah.ListSurahResponse
import com.eugene.qurku.response.surahdetails.SurahDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EQuranApi {
    @GET("/api/v2/surat")
    suspend fun getAllSurah(): Response<ListSurahResponse>

    @GET("/api/v2/surat/{number}")
    suspend fun getSurahDetails(@Path("number") number: Int): Response<SurahDetailResponse>

    @GET("/api/v2/tafsir/{number}")
    suspend fun getInterpretationDetails(@Path("number") number: Int): Response<InterpretationDetailResponse>
}