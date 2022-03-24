package com.company.activities

import androidx.lifecycle.ViewModel
import com.company.domain.interactor.analytics.AnalyticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by mochadwi on 3/24/22.
 * Copyright (c) 2022 sampingan.co.id. All rights reserved.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val analyticsUseCase: AnalyticsUseCase,
) : ViewModel() {
    suspend fun doSomething() = analyticsUseCase.getAnalyticsTags()
}