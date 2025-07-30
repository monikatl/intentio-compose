package com.baszczyk.intentioapp

import android.app.Application
import com.baszczyk.intentioapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IntentioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IntentioApp)
            modules(
                appModule
            )
        }
    }
}