package com.baszczyk.intentioapp

import android.app.Application
import com.baszczyk.intentioapp.di.appModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IntentioApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@IntentioApp)
            modules(
                appModule
            )
        }
    }
}