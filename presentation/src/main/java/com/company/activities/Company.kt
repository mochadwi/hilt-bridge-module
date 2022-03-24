/*
 * Copyright (c) 2020.
 * PT. Company
 */

package com.company.activities

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Company : Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()
    }
}