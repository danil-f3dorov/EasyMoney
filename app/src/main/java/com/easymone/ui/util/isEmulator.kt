package com.easymone.ui.util

import android.os.Build

val isEmulator: Boolean by lazy {
    return@lazy (
            // Android SDK emulator
            Build.MANUFACTURER == "Google" && Build.BRAND == "google" &&
                    ((Build.FINGERPRINT.startsWith("google/sdk_gphone_")
                            && Build.FINGERPRINT.endsWith(":user/release-keys")
                            && Build.PRODUCT.startsWith("sdk_gphone_")
                            && Build.MODEL.startsWith("sdk_gphone_"))
                            //alternative
                            || (Build.FINGERPRINT.startsWith("google/sdk_gphone64_") && (Build.FINGERPRINT.endsWith(":userdebug/dev-keys")
                            || (Build.FINGERPRINT.endsWith(":user/release-keys")) && Build.PRODUCT.startsWith("sdk_gphone64_")
                            && Build.MODEL.startsWith("sdk_gphone64_")))
                            //Google Play Games emulator https://play.google.com/googleplaygames https://developer.android.com/games/playgames/emulator#other-downloads
                            || (Build.MODEL == "HPE device" &&
                            Build.FINGERPRINT.startsWith("google/kiwi_") && Build.FINGERPRINT.endsWith(":user/release-keys")
                            && Build.BOARD == "kiwi" && Build.PRODUCT.startsWith("kiwi_"))
                            )
                    //
                    || Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")
                    //bluestacks
                    || "QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equals(Build.MANUFACTURER, ignoreCase = true)
                    //bluestacks
                    || Build.MANUFACTURER.contains("Genymotion")
                    || Build.HOST.startsWith("Build")
                    //MSI App Player
                    || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                    || Build.PRODUCT == "google_sdk")

}
