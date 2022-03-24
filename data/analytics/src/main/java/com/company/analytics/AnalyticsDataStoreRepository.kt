package com.company.analytics

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.company.analytics.datastore.get
import com.company.analytics.datastore.set
import com.company.analytics.model.AnalyticsTagsDto
import com.company.analytics.model.toAnalyticsTags
import com.company.analytics.model.toAnalyticsTagsDto
import com.company.domain.model.analytics.AnalyticsTags
import com.company.domain.repository.analytics.AnalyticsRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Created by mochadwi on 2/27/22.
 * Copyright (c) 2022 company.co.id. All rights reserved.
 */

internal val ANALYTIC_TAGS_DATASTORE = stringPreferencesKey("analytic_tags")

class AnalyticsDataStoreRepository @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>,
) : AnalyticsRepository {
    override suspend fun getAnalyticsTags(): AnalyticsTags? {
        // we use `.firstOrNull` instead of `.singleOrNull`
        // see: https://kotlinlang.slack.com/archives/CRJCTR5PD/p1647320572496279
        return with(dataStorePreferences[ANALYTIC_TAGS_DATASTORE].firstOrNull()) {
            val analyticTag = try {
                Json.decodeFromString<AnalyticsTagsDto>(this.orEmpty())
            } catch (e: SerializationException) {
                null
            }

            analyticTag?.toAnalyticsTags()
        }
    }

    override suspend fun saveAnalyticsTags(data: AnalyticsTags?) {
        dataStorePreferences.set(
            ANALYTIC_TAGS_DATASTORE,
            if (data == null) {
                null
            } else {
                Json.encodeToString(data.toAnalyticsTagsDto())
            },
        )
    }
}