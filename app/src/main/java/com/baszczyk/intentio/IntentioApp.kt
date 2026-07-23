package com.baszczyk.intentioapp

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.baszczyk.intentio.di.appModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IntentioApp : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        val app = FirebaseApp.initializeApp(this)
        if (app == null) {
            Log.e("IntentioApp", "FirebaseApp initialization FAILED")
        } else {
            Log.d("IntentioApp", "FirebaseApp initialized OK: ${app.name}")
        }

        startKoin {
            androidContext(this@IntentioApp)
            modules(
                appModule
            )
        }
    }
}