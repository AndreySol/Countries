package com.example.countries.data.mappers

import com.example.countries.data.api.dto.CountryDto
import com.example.countries.domain.entity.Country

fun CountryDto.toEntity() = Country(
    name = name,
    region = region,
    code = code,
    capital = capital
)
