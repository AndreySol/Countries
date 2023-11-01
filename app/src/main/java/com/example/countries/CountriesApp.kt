package com.example.countries

import android.app.Application
import com.example.countries.di.AppComposition

class CountriesApp : Application() {
    lateinit var appComposition: AppComposition

    override fun onCreate() {
        appComposition = AppComposition()
        super.onCreate()
    }
}