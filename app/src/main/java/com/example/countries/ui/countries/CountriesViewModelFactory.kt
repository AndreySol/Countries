package com.example.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countries.domain.usecase.RequestCountriesUseCase

class CountriesViewModelFactory(val requestCountriesUseCase: RequestCountriesUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountriesViewModel(requestCountriesUseCase) as T
    }
}