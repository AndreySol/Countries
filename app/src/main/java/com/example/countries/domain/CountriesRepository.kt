package com.example.countries.domain

import com.example.countries.common.Result
import com.example.countries.domain.entity.Country

interface CountriesRepository {
    suspend fun requestCountries(): List<Country>
}