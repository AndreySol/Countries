package com.example.countries.di

import com.example.countries.data.CountriesRepositoryImpl
import com.example.countries.data.RemoteDataSource
import com.example.countries.data.api.CountriesApi
import com.example.countries.data.api.RetrofitServer
import com.example.countries.domain.CountriesRepository
import com.example.countries.domain.usecase.RequestCountriesUseCase

class AppComposition {
    private val countriesApi = RetrofitServer.retrofit.create(CountriesApi::class.java)

    private val remoteDataSource: RemoteDataSource by lazy { RemoteDataSource(countriesApi) }

    private val repository: CountriesRepository by lazy { CountriesRepositoryImpl(remoteDataSource) }

    val fetchRequestCountriesUseCase get() = RequestCountriesUseCase(repository)
}