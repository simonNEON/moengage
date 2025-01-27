package com.moengage.sampleapp

import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.config.PushKitConfig
import com.moengage.core.model.SdkState
import com.moengage.flutter.MoEInitializer
import com.moengage.push.amp.plus.MiPushHelper.initialiseMiPush
import com.moengage.pushbase.MoEPushHelper
import com.xiaomi.channel.commonutils.android.Region
import io.flutter.app.FlutterApplication

/**
 * @author Umang Chamaria
 * Date: 2019-12-13
 */
class SampleApplication : FlutterApplication() {
    override fun onCreate() {
        super.onCreate()
        val moEngage: MoEngage.Builder = MoEngage.Builder(this, "DAO6UGZ73D9RTK8B5W96TPYN")
            .configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.icon,
                    R.drawable.ic_launcher,
                    notificationColor = -1,
                    isMultipleNotificationInDrawerEnabled = false,
                    isBuildingBackStackEnabled = true,
                    isLargeIconDisplayEnabled = true
                )
            )
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureFcm(FcmConfig(true))
            .configurePushKit(PushKitConfig(true))
        initialiseMiPush(
            context = this,
            appKey = "5601804211309",
            appId = "2882303761518042309",
            region = Region.India
        )
        MoEInitializer.initialiseDefaultInstance(applicationContext, moEngage, SdkState.ENABLED,true)
        // optional, required in-case notification customisation is required.
        MoEPushHelper.getInstance().registerMessageListener(CustomPushListener())
    }
}