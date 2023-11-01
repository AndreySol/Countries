package com.example.countries.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.ErrorCode
import com.example.countries.common.Result
import com.example.countries.domain.entity.Country
import com.example.countries.domain.usecase.RequestCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val requestCountriesUseCase: RequestCountriesUseCase
) : ViewModel() {

    private val _flow = MutableStateFlow<CountriesUiState>(CountriesUiState.Loading)
    val flow = _flow

    init {
        viewModelScope.launch {
            when (val result = requestCountriesUseCase()) {
                is Result.Failure -> showError(result.errorCode)
                is Result.Success -> updateContent(result.data)
            }
        }
    }

    private fun showError(errorCode: ErrorCode) {
        _flow.update {
            CountriesUiState.Failure(errorCode)
        }
    }

    private fun updateContent(countries: List<Country>) {
        _flow.update {
            CountriesUiState.Loaded(
                data = countries
            )
        }
    }
}