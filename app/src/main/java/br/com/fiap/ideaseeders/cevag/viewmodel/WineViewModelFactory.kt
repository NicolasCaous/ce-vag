package br.com.fiap.ideaseeders.cevag.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WineViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WineViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
