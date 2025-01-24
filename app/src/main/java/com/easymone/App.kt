package com.easymone

import android.app.Application
import com.progun.dunta_sdk.api.DuntaManager
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var duntaManager: DuntaManager
            private set
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        duntaManager = DuntaManager.create(this)
        super.onCreate()
        instance = this
    }
}
