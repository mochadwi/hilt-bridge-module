/*
 * Copyright (c) 2020.
 * PT. Company
 */

package com.company.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Hello ${viewModel.state.value.analyticsTags}")
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getIntent().data = intent?.data
    }
}