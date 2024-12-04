package com.example.objectremovercompose.ui.navigations

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.objectremovercompose.ui.navigations.Routes.AI_REMOVER_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.EXIT_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.FIRST_LANGUAGE_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.HOME_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.LANGUAGE_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.MANUAL_EDITOR_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.My_CREATION_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.ON_BOARD_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.SAVE_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.SETTING_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.SPLASH_SCREEN
import com.example.objectremovercompose.ui.navigations.Routes.UPGRADE_SCREEN


fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}

fun NavOptionsBuilder.popUpToEditor() {
    popUpTo(NavRoute.ManualEditorScreen.route) { // Specify the editor screen route
        inclusive = true // Include the editor screen in the pop
    }
}

private object Routes {
    const val SPLASH_SCREEN = "SPLASH_SCREEN"
    const val UPGRADE_SCREEN = "UPGRADE_SCREEN"
    const val ON_BOARD_SCREEN = "ON_BOARD_SCREEN"
    const val HOME_SCREEN = "HOME_SCREEN"
    const val My_CREATION_SCREEN = "My_CREATION_SCREEN"
    const val EXIT_SCREEN = "EXIT_SCREEN"
    const val LANGUAGE_SCREEN = "LANGUAGE_SCREEN"
    const val FIRST_LANGUAGE_SCREEN = "FIRST_LANGUAGE_SCREEN"
    const val SETTING_SCREEN = "SETTING_SCREEN"

    // product details
    const val MANUAL_EDITOR_SCREEN = "MANUAL_EDITOR_SCREEN"
    const val AI_REMOVER_SCREEN = "AI_REMOVER_SCREEN"
    const val SAVE_SCREEN = "SAVE_SCREEN"
}

sealed class NavRoute(val route: String) {
    data object SplashScreen : NavRoute(SPLASH_SCREEN)
    data object UpgradeScreen : NavRoute(UPGRADE_SCREEN)

    data object OnBoardingScreen : NavRoute(ON_BOARD_SCREEN)

    data object HomeScreen : NavRoute(HOME_SCREEN)
    data object CreationScreen : NavRoute(My_CREATION_SCREEN)
    data object ExitScreen : NavRoute(EXIT_SCREEN)
    data object LanguageScreen : NavRoute(LANGUAGE_SCREEN)
    data object FirstLanguageScreen : NavRoute(FIRST_LANGUAGE_SCREEN)

    data object SettingScreen : NavRoute(SETTING_SCREEN)
    data object ManualEditorScreen : NavRoute(MANUAL_EDITOR_SCREEN)
    data object AiRemoverScreen : NavRoute(AI_REMOVER_SCREEN)
    data object SaveScreen : NavRoute(SAVE_SCREEN)

}