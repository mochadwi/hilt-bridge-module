package com.company.analytics.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @deprecated // TODO(mochadwi): 3/14/22 moved to :data:datastorepreferences later :pray:
 *
 * Temporary added in :data:analytics for scoping migration
 */

@Deprecated(
    message = "will be `internal` modifier to prevent direct access from presentation?"
)
operator fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>): Flow<T?> {
    return this.data.map { it[key] }
}

@Deprecated(
    message = "will be `internal` modifier to prevent direct access from presentation?"
)
suspend fun <T> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T?) {
    if (value == null) this.remove(key)
    else this.save(key, value)
}

private suspend fun <T> DataStore<Preferences>.save(key: Preferences.Key<T>, value: T) {
    this.edit { preferences -> preferences[key] = value }
}

private suspend fun <T> DataStore<Preferences>.remove(key: Preferences.Key<T>) {
    this.edit { preferences -> preferences.remove(key) }
}