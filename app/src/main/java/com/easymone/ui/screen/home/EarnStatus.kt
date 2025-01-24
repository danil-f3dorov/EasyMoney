package com.easymone.ui.screen.home

sealed class EarnStatus {
    data object Connected: EarnStatus()
    data object Default: EarnStatus()
    data object EmulatorEnabled : EarnStatus()
    data object VpnEnabled : EarnStatus()
}
