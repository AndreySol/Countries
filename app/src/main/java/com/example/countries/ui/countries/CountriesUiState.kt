package com.example.countries.ui.countries

import com.example.countries.common.ErrorCode
import com.example.countries.domain.entity.Country

sealed interface CountriesUiState {
    object Loading : CountriesUiState
    data class Loaded(val data: List<Country>) : CountriesUiState
    data class Failure(val errorCode: ErrorCode) : CountriesUiState
}
