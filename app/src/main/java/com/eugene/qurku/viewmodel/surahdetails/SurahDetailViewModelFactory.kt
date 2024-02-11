package com.eugene.qurku.viewmodel.surahdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eugene.qurku.repository.SurahDetailRepository

class SurahDetailViewModelFactory(private val repository: SurahDetailRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SurahDetailViewModel(repository) as T
    }
}