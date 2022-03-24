package com.company.activities

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.interactor.analytics.AnalyticsUseCase
import com.company.domain.model.analytics.AnalyticsTags
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mochadwi on 3/24/22.
 * Copyright (c) 2022 sampingan.co.id. All rights reserved.
 */

data class MainState(
    val analyticsTags: AnalyticsTags? = null,
)

@HiltViewModel
class MainViewModel @Inject constructor(
    val analyticsUseCase: AnalyticsUseCase,
) : ViewModel() {
    val state = mutableStateOf(MainState())

    fun getAnalyticsTags() {
        viewModelScope.launch {
            state.value = MainState(analyticsTags = analyticsUseCase.getAnalyticsTags())
        }
    }
}