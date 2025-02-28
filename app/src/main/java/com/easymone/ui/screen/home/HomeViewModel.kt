package com.easymone.ui.screen.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.App
import com.easymone.data.local.UserPreferences
import com.easymone.data.remote.api.EarnStatsApi
import com.easymone.data.remote.model.request.GetStatsRequest
import com.easymone.data.remote.model.response.ClientStats
import com.easymone.data.remote.model.response.DailyStats
import com.easymone.ui.util.getStartDay
import com.easymone.ui.util.getToday
import com.easymone.ui.util.isEmulator
import com.easymone.ui.util.isVpn
import com.progun.dunta_sdk.android.core.DuntaService
import com.progun.dunta_sdk.api.DuntaManager
import com.progun.dunta_sdk.api.DuntaManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val earnStatsApi: EarnStatsApi,
    private val userPreferences: UserPreferences
) : ViewModel() {
    var loading = mutableStateOf(false)

    init {
        loading.value = true
    }

    private val _dailyStats = MutableStateFlow(emptyList<DailyStats>())
    val dailyStats get() = _dailyStats
    private val _clientStats = MutableStateFlow(ClientStats("0.000", "0.0000"))
    val clientStats get() = _clientStats
    var isExpanded = mutableStateOf(false)
    private var _earnStatus = MutableStateFlow(
        if (DuntaService.serviceIsRunning) EarnStatus.Connected
        else EarnStatus.Default
    )
    val earnStatus get() = _earnStatus

    fun startEarn() {
        if (isEmulator) {
            _earnStatus.value = EarnStatus.EmulatorEnabled
            return
        }
        if (isVpn(App.instance)) {
            _earnStatus.value = EarnStatus.VpnEnabled
            return
        }

        App.duntaManager.setNotificationTitle(App.instance, "Earning in process")
        updateNotificationContent(clientStats.value.balance)

        App.duntaManager.setPartnerId(1)
        App.duntaManager.setApplicationId(100)
        App.duntaManager.start(App.instance)

        viewModelScope.launch {
            clientStats.collect { stats ->
                updateNotificationContent(stats.balance)
            }
        }

        _earnStatus.value = EarnStatus.Connected
    }

    private fun updateNotificationContent(balance: String) {
        val formattedBalance = String.format(Locale.ENGLISH, "%.3f", balance.toFloat())
        App.duntaManager.setNotificationContent(App.instance, "You earn $formattedBalance$")
    }

    fun stopEarn() {
        App.duntaManager.stop(App.instance)
        _earnStatus.value = EarnStatus.Default
    }

    fun defaultEarnStatus() {
        _earnStatus.value = EarnStatus.Default
    }

    fun getStats(
        navNoInternet: () -> Unit
    ) {
        viewModelScope.launch {
            isExpanded.value = false
            loading.value = true
            try {
                val token = userPreferences.getToken() ?: ""
                val today = getToday()
                val startDay = getStartDay()


                val response = earnStatsApi.getStats(
                    GetStatsRequest(
                        token = token,
                        start = startDay,
                        end = today
                    )
                ).body()

                if (response != null && response.result == 0) {
                    _dailyStats.value = response.dailyStats
                    _clientStats.value = response.clientStats
                }

                loading.value = false
                isExpanded.value = true
            } catch (e: UnknownHostException) {
                navNoInternet()
            } finally {
                loading.value = false
            }
        }
    }
}
