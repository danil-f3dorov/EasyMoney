package com.easymone.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.easymone.ui.screen.home.HomeScreen
import com.easymone.ui.screen.login.LoginScreen
import com.easymone.ui.screen.signup.SignUpScreen
import com.easymone.ui.screen.start.StartScreen
import kotlinx.serialization.Serializable

@Composable
fun MainScreen(
    navController: NavHostController
) {

    val navHome = {
        navController.navigate(Screen.HomeScreen) {
            popUpTo(Screen.Start) { inclusive = true }
        }
    }
    val navSignUp = { navController.navigate(Screen.SignUpScreen){
        launchSingleTop = true
    } }
    val navLogin = { navController.navigate(Screen.LoginScreen) {
        launchSingleTop = true
    } }
    val popBackStack = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Screen.Start
    ) {
        composable<Screen.Start> {
            StartScreen(
                navHome = navHome,
                navSignUp = navSignUp
            )
        }
        composable<Screen.SignUpScreen> {
            SignUpScreen(
                navLogin = navLogin,
                navHome = navHome,
                popBackStack = popBackStack
            )

        }
        composable<Screen.LoginScreen> {
            LoginScreen(
                navSignUp = navSignUp,
                navHome = navHome,
                popBackStack = popBackStack
            )
        }
        composable<Screen.HomeScreen> {
            HomeScreen()
        }
    }
}


sealed class Screen {
    @Serializable
    data object Start : Screen()

    @Serializable
    data object SignUpScreen : Screen()

    @Serializable
    data object LoginScreen : Screen()

    @Serializable
    data object HomeScreen : Screen()
}