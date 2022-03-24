package com.company.domain.repository.analytics

import com.company.domain.model.analytics.AnalyticsTags

/**
 * Created by mochadwi on 2/27/22.
 * Copyright (c) 2022 company.co.id. All rights reserved.
 */
interface AnalyticsRepository {
    suspend fun getAnalyticsTags(): AnalyticsTags?
    suspend fun saveAnalyticsTags(data: AnalyticsTags?)
}