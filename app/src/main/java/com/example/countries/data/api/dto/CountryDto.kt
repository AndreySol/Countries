package com.example.countries.data.api.dto

import com.example.countries.domain.entity.Country

data class CountryDto(
    val capital: String,
    val code: String,
    val currency: CurrencyDto,
    val demonym: String,
    val flag: String,
    val language: LanguageDto,
    val name: String,
    val region: String
)





