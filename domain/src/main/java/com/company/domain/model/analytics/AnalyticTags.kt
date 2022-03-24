package com.company.domain.model.analytics

/**
 * Created by mochadwi on 2/27/22.
 * Copyright (c) 2022 company.co.id. All rights reserved.
 */

data class AnalyticsTags(
    val marketingTitle: String,
    val referringLink: String, // short url
    val feature: String,
    val channel: String,
    val campaign: String,
    val tags: String,
)

// <editor-fold desc="Obtained from branch.io">
const val ANALYTICS_UNIQUE_MARKETING_TITLE = "\$marketing_title"
const val ANALYTICS_UNIQUE_REFERRING_LINK = "~referring_link"
const val ANALYTICS_UNIQUE_FEATURE = "~feature"
const val ANALYTICS_UNIQUE_CHANNEL = "~channel"
const val ANALYTICS_UNIQUE_CAMPAIGN = "~campaign"
const val ANALYTICS_UNIQUE_TAGS = "~tags"

const val EVENT_PROPERTY_ANALYTICS_MARKETING_TITLE = "marketingTitle"
// from `referring_link`
const val EVENT_PROPERTY_ANALYTICS_SHORT_URL = "shortUrl"
const val EVENT_PROPERTY_ANALYTICS_FEATURE = "feature"
const val EVENT_PROPERTY_ANALYTICS_CHANNEL = "channel"
const val EVENT_PROPERTY_ANALYTICS_CAMPAIGN = "campaign"
const val EVENT_PROPERTY_ANALYTICS_TAGS = "tags"
// </editor-fold>

fun AnalyticsTags?.toMap() = mapOf<String, Any>(
    EVENT_PROPERTY_ANALYTICS_MARKETING_TITLE to this?.marketingTitle.orEmpty(),
    EVENT_PROPERTY_ANALYTICS_CAMPAIGN to this?.campaign.orEmpty(),
    EVENT_PROPERTY_ANALYTICS_CHANNEL to this?.channel.orEmpty(),
    EVENT_PROPERTY_ANALYTICS_FEATURE to this?.feature.orEmpty(),
    EVENT_PROPERTY_ANALYTICS_SHORT_URL to this?.referringLink.orEmpty(),
    EVENT_PROPERTY_ANALYTICS_TAGS to this?.tags.orEmpty(),
)

private const val BODY_MARKETING_TITLE = "marketing_title"
private const val BODY_MARKETING_SHORT_URL = "marketing_short_url"
private const val BODY_MARKETING_FEATURE = "marketing_feature"
private const val BODY_MARKETING_CHANNEL = "marketing_channel"
private const val BODY_MARKETING_CAMPAIGN = "marketing_campaign"
private const val BODY_MARKETING_TAGS = "marketing_tags"

fun AnalyticsTags?.asBodyMap() = mapOf<String, Any>(
    BODY_MARKETING_TITLE to this?.marketingTitle.orEmpty(),
    BODY_MARKETING_SHORT_URL to this?.referringLink.orEmpty(),
    BODY_MARKETING_FEATURE to this?.feature.orEmpty(),
    BODY_MARKETING_CHANNEL to this?.channel.orEmpty(),
    BODY_MARKETING_CAMPAIGN to this?.campaign.orEmpty(),
    BODY_MARKETING_TAGS to this?.tags.orEmpty(),
)