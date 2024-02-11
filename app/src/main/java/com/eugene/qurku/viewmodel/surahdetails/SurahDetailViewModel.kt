package com.eugene.qurku.viewmodel.surahdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugene.qurku.repository.SurahDetailRepository
import com.eugene.qurku.response.interpretation.InterpretationDetailResponse
import com.eugene.qurku.response.surahdetails.SurahDetailResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class SurahDetailViewModel(private val repository: SurahDetailRepository): ViewModel() {
    val dataSurahDetails: MutableLiveData<Response<SurahDetailResponse>> = MutableLiveData()
    val dataInterpretationDetails: MutableLiveData<Response<InterpretationDetailResponse>> = MutableLiveData()
    val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()

    fun getData(number: Int){
        viewModelScope.launch {
            isLoadingData.value = true
            dataSurahDetails.value = repository.getSurahDetail(number)
            dataInterpretationDetails.value = repository.getInterpretationDetail(number)
            isLoadingData.value = false
        }
    }
}