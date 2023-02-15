package com.example.test.DI

import android.app.Application
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Tanvir3488 on 2/14/2023.
 */

const val ONESIGNAL_APP_ID = "47a3caf6-3304-49aa-bc4e-86b7548c2cf6"
@HiltAndroidApp
class TestApp: Application() {
     override fun onCreate() {
          super.onCreate()

          // Logging set to help debug issues, remove before releasing your app.
          OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

          // OneSignal Initialization
          OneSignal.initWithContext(this)
          OneSignal.setAppId(ONESIGNAL_APP_ID)
     }
}