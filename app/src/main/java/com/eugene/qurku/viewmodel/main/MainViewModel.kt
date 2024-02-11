package com.eugene.qurku.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugene.qurku.repository.MainRepository
import com.eugene.qurku.response.listsurah.ListSurahResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    val data: MutableLiveData<Response<ListSurahResponse>> = MutableLiveData()
    val isLoadingGetData: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllSurah(){
        viewModelScope.launch {
            isLoadingGetData.value = true
            data.value = mainRepository.getAllSurah()
            isLoadingGetData.value = false
        }
    }
}