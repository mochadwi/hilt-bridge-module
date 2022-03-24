package com.company.analytics

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.company.analytics.AnalyticsDataStoreRepository
import com.company.domain.repository.analytics.AnalyticsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface BindAnalyticsModule {
    @get:[Binds]
    val AnalyticsDataStoreRepository.analyticsRepo: AnalyticsRepository
}

internal const val DEFAULT_DATASTORE = "_datastore"

@InstallIn(SingletonComponent::class)
@Module
object ProvideAnalyticsModule {
    @Singleton
    @Provides
    fun provideDataStorePreference(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        val packageName = context.packageName
        val dataStoreName = "$packageName$DEFAULT_DATASTORE"
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(dataStoreName) }
        )
    }
}