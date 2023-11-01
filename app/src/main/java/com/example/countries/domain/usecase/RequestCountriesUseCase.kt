package com.example.countries.domain.usecase

import com.example.countries.common.ApiException
import com.example.countries.common.AppLogger
import com.example.countries.common.ErrorCode
import com.example.countries.common.Result
import com.example.countries.domain.CountriesRepository
import com.example.countries.domain.entity.Country

class RequestCountriesUseCase(
    private val repository: CountriesRepository
) {

    suspend operator fun invoke(): Result<List<Country>> {
        return try {
            Result.Success(repository.requestCountries())
        } catch (e: ApiException) {
            AppLogger.error(e)
            Result.Failure(errorCode = e.errorCode)
        } catch (e: Exception) {
            AppLogger.error(e)
            Result.Failure(errorCode = ErrorCode.UNKNOWN_ERROR)
        }
    }
}