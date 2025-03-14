package com.easymone.ui.screen.home





sealed class RestrictionType {
    object Vpn: RestrictionType()
    object Emulator: RestrictionType()
}