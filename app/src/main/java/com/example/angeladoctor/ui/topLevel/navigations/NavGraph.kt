package com.example.angeladoctor.ui.topLevel.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.angeladoctor.ui.clean.data.model.Drug
import com.example.angeladoctor.ui.clean.presentation.common.addScreenWithTransitions
import com.example.angeladoctor.ui.clean.presentation.disease.DiseaseDetails
import com.example.angeladoctor.ui.clean.presentation.disease.DiseaseScreen
import com.example.angeladoctor.ui.clean.presentation.login.LoginScreen
import com.example.angeladoctor.ui.clean.presentation.splash.SplashScreen
import com.google.gson.Gson

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = NavRoute.SplashScreen.route
    ) {
        addSplashScreen(navController, this)
        addLoginScreen(navController, this)
        addDiseaseScreen(navController, this)
        addDiseaseDetailsScreen(navController, this)
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

private fun addDiseaseScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.addScreenWithTransitions(route = NavRoute.DiseaseScreen.route) {
        DiseaseScreen(navController = navController)
    }
}

private fun addDiseaseDetailsScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.addScreenWithTransitions(
        route = "${NavRoute.DiseaseDetailsScreen.route}/{drug}",
        arguments = listOf(navArgument("drug") { type = NavType.StringType })
    ) {
        val drugJson = navController.currentBackStackEntry?.arguments?.getString("drug")
        val drug = Gson().fromJson(drugJson, Drug::class.java) // Decode drug JSON
        if (drug != null) {
            DiseaseDetails(navController = navController, drug)
        }
    }
}
