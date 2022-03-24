package com.company.analytics.model

import com.company.domain.model.analytics.ANALYTICS_UNIQUE_CAMPAIGN
import com.company.domain.model.analytics.ANALYTICS_UNIQUE_CHANNEL
import com.company.domain.model.analytics.ANALYTICS_UNIQUE_FEATURE
import com.company.domain.model.analytics.ANALYTICS_UNIQUE_MARKETING_TITLE
import com.company.domain.model.analytics.ANALYTICS_UNIQUE_REFERRING_LINK
import com.company.domain.model.analytics.ANALYTICS_UNIQUE_TAGS
import kotlinx.serialization.Serializable
import org.json.JSONObject
import com.company.domain.model.analytics.AnalyticsTags as AnalyticsTagsDomain

/**
 * Created by mochadwi on 2/27/22.
 * Copyright (c) 2022 company.co.id. All rights reserved.
 */

@Serializable
internal data class AnalyticsTagsDto(
    val marketing_title: String? = null,
    val referring_link: String? = null,
    val feature: String? = null,
    val channel: String? = null,
    val campaign: String? = null,
    val tags: String? = null,
)

internal fun AnalyticsTagsDto.toAnalyticsTags() = AnalyticsTagsDomain(
    marketingTitle = marketing_title.orEmpty(),
    feature = feature.orEmpty(),
    channel = channel.orEmpty(),
    campaign = campaign.orEmpty(),
    referringLink = referring_link.orEmpty(),
    tags = tags?.asString().orEmpty(),
)

internal fun AnalyticsTagsDomain.toAnalyticsTagsDto() = AnalyticsTagsDto(
    marketing_title = marketingTitle,
    feature = feature,
    channel = channel,
    campaign = campaign,
    referring_link = referringLink,
    tags = tags.asString(),
)

fun JSONObject?.hasAnalyticsTags() =
    this?.isNull(ANALYTICS_UNIQUE_MARKETING_TITLE) == false
        || this?.isNull(ANALYTICS_UNIQUE_CAMPAIGN) == false
        || this?.isNull(ANALYTICS_UNIQUE_TAGS) == false
        || this?.isNull(ANALYTICS_UNIQUE_REFERRING_LINK) == false
        || this?.isNull(ANALYTICS_UNIQUE_CHANNEL) == false
        || this?.isNull(ANALYTICS_UNIQUE_FEATURE) == false

fun JSONObject?.parseAnalyticsTags() = AnalyticsTagsDomain(
    marketingTitle = this?.optString(ANALYTICS_UNIQUE_MARKETING_TITLE).orEmpty(),
    referringLink = this?.optString(ANALYTICS_UNIQUE_REFERRING_LINK).orEmpty(),
    feature = this?.optString(ANALYTICS_UNIQUE_FEATURE).orEmpty(),
    channel = this?.optString(ANALYTICS_UNIQUE_CHANNEL).orEmpty(),
    campaign = this?.optString(ANALYTICS_UNIQUE_CAMPAIGN).orEmpty(),
    tags = this?.optJSONArray(ANALYTICS_UNIQUE_TAGS)?.join(", ").orEmpty()
)

fun String.asString() = this.removeSurrounding("\"")