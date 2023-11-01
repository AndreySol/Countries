package com.example.countries.common

class ApiException(
    val errorCode: ErrorCode,
    val errorMsg: String?
): Exception(errorMsg)