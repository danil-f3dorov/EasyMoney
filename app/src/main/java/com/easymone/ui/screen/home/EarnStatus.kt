package com.easymone.ui.screen.home

import com.easymone.R

sealed class EarnStatus {
    abstract val buttonIconId: Int
    abstract val buttonText: String
    data object Connected: EarnStatus() {
        override val buttonIconId: Int
            get() = R.drawable.ic_off_wifi
        override val buttonText: String
            get() = "Stop Sharing"
    }

    data object Default: EarnStatus() {
        override val buttonIconId: Int
            get() = R.drawable.wifi_line
        override val buttonText: String
            get() = "Start Sharing"
    }
}
