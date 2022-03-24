/*
 * Copyright (c) 2020.
 * PT. Company
 */

package com.company.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import com.company.domain.interactor.analytics.AnalyticsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var analyticsUseCase: AnalyticsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Hello branch from main")
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getIntent().data = intent?.data
    }
}