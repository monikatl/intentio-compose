package com.baszczyk.intentioapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.baszczyk.intentioapp.utils.dataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(private val context: Context) {

    val settingsFlow: Flow<Boolean> = context.dataStore.data
        .map { it[DataStoreKeys.SHOW_DESCRIPTIONS] ?: false }

    suspend fun setShowDescriptions(enabled: Boolean) {
        context.dataStore.edit { it[DataStoreKeys.SHOW_DESCRIPTIONS] = enabled }
    }
}