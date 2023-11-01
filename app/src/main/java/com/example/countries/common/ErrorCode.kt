package com.example.countries.common

import android.content.Context
import com.example.countries.R

enum class ErrorCode {
    UNKNOWN_ERROR,
    CONNECTION_ERROR,
    SERVER_ERROR;

    fun toMessage(ctx: Context?): String {
        if (ctx == null) {
            return "Unknown error"
        }

        return when (this) {
            CONNECTION_ERROR -> ctx.getString(R.string.error_connection)
            SERVER_ERROR -> ctx.getString(R.string.error_server)
            UNKNOWN_ERROR -> ctx.getString(R.string.error_unknown)
        }
    }
}