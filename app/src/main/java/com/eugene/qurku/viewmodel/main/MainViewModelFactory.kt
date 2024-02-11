package com.eugene.qurku.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eugene.qurku.repository.MainRepository

class MainViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}