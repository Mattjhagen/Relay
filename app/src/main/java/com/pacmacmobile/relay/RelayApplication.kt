package com.pacmacmobile.relay

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RelayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
