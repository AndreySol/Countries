package com.example.countries.common

import java.util.logging.Logger

object AppLogger {
    private const val TAG = "LoggerCountriesTag"

    fun info(msg: String) {
        Logger.getLogger(TAG).info(msg)
    }

    fun error(e: Throwable) {
        Logger.getLogger(TAG).warning(e.message ?: "Unknown error")
        e.printStackTrace()
    }
}