package com.example.countries.data

import com.example.countries.common.ApiException
import com.example.countries.common.ErrorCode
import com.example.countries.data.api.CountriesApi
import com.example.countries.data.api.dto.CountryDto
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RemoteDataSource(
    private val api: CountriesApi
) {
    suspend fun requestCountries(): List<CountryDto>? {

        val response: Response<List<CountryDto>>

        try {
            response = api.requestCountries()
        } catch (e: UnknownHostException) {
            throw ApiException(
                errorCode = ErrorCode.CONNECTION_ERROR,
                errorMsg = e.message
            )
        } catch (e: SocketTimeoutException) {
            throw ApiException(
                errorCode = ErrorCode.CONNECTION_ERROR,
                errorMsg = e.message
            )
        }

        if (response.isSuccessful) {
            return response.body()
        } else {
            throw ApiException(
                errorCode = ErrorCode.SERVER_ERROR,
                errorMsg = response.errorBody().toString()
            )
        }
    }
}