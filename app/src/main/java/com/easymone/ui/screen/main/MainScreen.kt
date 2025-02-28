package com.easymone.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.fastCbrt
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.easymone.data.local.UserPreferences
import com.easymone.ui.screen.nointernet.NoInternetScreen
import com.easymone.ui.screen.home.HomeScreen
import com.easymone.ui.screen.login.LoginScreen
import com.easymone.ui.screen.signup.SignUpScreen
import com.easymone.ui.screen.start.StartScreen
import com.easymone.ui.util.isInternetAvailable
import kotlinx.serialization.Serializable

@Composable
fun MainScreen(
    navController: NavHostController
) {
    var navFlag by remember { mutableStateOf(false) }
    val userPrefs = UserPreferences(LocalContext.current)

    val navHome = {
        navController.navigate(Screen.HomeScreen) {
            popUpTo(Screen.Start) { inclusive = true }
        }
    }
    val navSignUp = {
        navController.navigate(Screen.SignUpScreen) {
            launchSingleTop = true
        }
    }

    fun navLoginWithArgs(email: String = "", password: String = "") {
        navController.navigate(Screen.LoginScreen(email, password))
    }

    val navNoInternet = { navController.navigate(Screen.NoInternetScreen) }

    val popBackStack = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination =
        if (!isInternetAvailable()) {
            navFlag = true
            Screen.NoInternetScreen
        } else if (userPrefs.isUserLoggedIn()) Screen.HomeScreen
        else Screen.Start
    ) {
        composable<Screen.Start> {
            StartScreen(
                navHome = navHome,
                navSignUp = navSignUp,
                navNoInternet = navNoInternet
            )
        }
        composable<Screen.SignUpScreen> {
            SignUpScreen(
                navLogin = { navLoginWithArgs() },
                navNoInternet = navNoInternet,
                popBackStack = popBackStack,
                navLoginWithArgs = { email, password ->
                    navLoginWithArgs(email, password)
                }
            )
        }
        composable<Screen.LoginScreen> {
            LoginScreen(
                navSignUp = navSignUp,
                navHome = navHome,
                navNoInternet = navNoInternet,
                popBackStack = popBackStack,
                initEmail = it.arguments?.getString("email") ?: "",
                initPassword = it.arguments?.getString("password") ?: ""
            )
        }
        composable<Screen.HomeScreen> {
            HomeScreen(
                navNoInternet = navNoInternet
            )
        }

        composable<Screen.NoInternetScreen> {
            NoInternetScreen {
                if (navFlag) {
                    navController.navigate(
                        if (userPrefs.isUserLoggedIn()) Screen.HomeScreen
                        else Screen.Start
                    ) {
                        popUpTo(Screen.NoInternetScreen) { inclusive = true }
                    }
                    navFlag = false
                } else {
                    popBackStack()
                }
            }
        }
    }
}

sealed class Screen {
    @Serializable
    data object Start : Screen()

    @Serializable
    data object SignUpScreen : Screen()

    @Serializable
    data class LoginScreen(val email: String, val password: String) : Screen()

    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object NoInternetScreen : Screen()
}