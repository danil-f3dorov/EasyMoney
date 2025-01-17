package com.easymone.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymone.data.local.UserPreferences
import com.easymone.data.remote.api.EarnStatsApi
import com.easymone.data.remote.model.request.GetStatsRequest
import com.easymone.data.remote.model.response.ClientStats
import com.easymone.data.remote.model.response.DailyStats
import com.easymone.ui.util.getStartDay
import com.easymone.ui.util.getToday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val earnStatsApi: EarnStatsApi,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _dailyStats = MutableStateFlow(emptyList<DailyStats>())
    val dailyStats get() = _dailyStats
    private val _clientStats = MutableStateFlow(ClientStats("0.000", "0.0000"))
    val clientStats get() = _clientStats

    fun getStats() {
        viewModelScope.launch {
            val token = userPreferences.getToken() ?: ""
            val today = getToday()
            val startDay = getStartDay()


            val response = earnStatsApi.getStats(GetStatsRequest(token = token, start = startDay, end = today)).body()

            if (response != null && response.result == 0) {
                _dailyStats.value = response.dailyStats
                _clientStats.value = response.clientStats
            }
        }
    }
}
