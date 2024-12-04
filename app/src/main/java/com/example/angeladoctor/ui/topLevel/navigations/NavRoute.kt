package com.example.angeladoctor.ui.topLevel.navigations

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.angeladoctor.ui.topLevel.navigations.Routes.LOGIN_SCREEN
import com.example.angeladoctor.ui.topLevel.navigations.Routes.SPLASH_SCREEN


fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}


private object Routes {
    const val SPLASH_SCREEN = "SPLASH_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
}

sealed class NavRoute(val route: String) {
    data object SplashScreen : NavRoute(SPLASH_SCREEN)
    data object LoginScreen : NavRoute(LOGIN_SCREEN)

}