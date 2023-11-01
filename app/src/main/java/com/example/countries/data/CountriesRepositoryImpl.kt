package com.example.countries.data

import com.example.countries.data.mappers.toEntity
import com.example.countries.domain.CountriesRepository
import com.example.countries.domain.entity.Country

class CountriesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CountriesRepository {
    override suspend fun requestCountries(): List<Country> {

        return remoteDataSource.requestCountries()?.map { country ->
            country.toEntity()
        } ?: emptyList()
    }
}