package com.example.angeladoctor.ui.topLevel.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.angeladoctor.ui.clean.presentation.common.addScreenWithTransitions
import com.example.angeladoctor.ui.clean.presentation.login.LoginScreen
import com.example.angeladoctor.ui.clean.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = NavRoute.SplashScreen.route
    ) {
        addSplashScreen(navController, this)
        addLoginScreen(navController, this)
    }
}

private fun addSplashScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.addScreenWithTransitions(route = NavRoute.SplashScreen.route) {
        SplashScreen(navController = navController)
    }
}

private fun addLoginScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.addScreenWithTransitions(route = NavRoute.LoginScreen.route) {
        LoginScreen(navController = navController)
    }
}
