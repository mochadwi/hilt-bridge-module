package com.company.domain.interactor.analytics

import com.company.domain.model.analytics.AnalyticsTags
import com.company.domain.model.analytics.asBodyMap
import com.company.domain.model.analytics.toMap
import com.company.domain.repository.analytics.AnalyticsRepository
import javax.inject.Inject

/**
 * Created by mochadwi on 2/27/22.
 * Copyright (c) 2022 company.co.id. All rights reserved.
 */

class AnalyticsUseCase @Inject constructor(
    private val repository: AnalyticsRepository,
) {
    suspend fun getAnalyticsTags() = repository.getAnalyticsTags()
    suspend fun getAnalyticsTagsAsMap(isForEventTracking: Boolean) = if (isForEventTracking) {
        repository.getAnalyticsTags().toMap()
    } else {
        repository.getAnalyticsTags().asBodyMap()
    }

    /**
     * [tags] nullable means delete the previous [AnalyticsTags]
     */
    suspend fun saveAnalyticsTags(tags: AnalyticsTags?) = repository.saveAnalyticsTags(tags)
}