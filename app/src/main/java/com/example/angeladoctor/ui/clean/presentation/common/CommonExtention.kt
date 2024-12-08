package com.example.angeladoctor.ui.clean.presentation.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

@Composable
fun getActivity(): Activity? {
    return LocalContext.current as? Activity
}


val gradientBrushHorizontal = Brush.horizontalGradient(
    colors = listOf(Color(0xFF751BF7), Color(0xFF9249FB))
)

val gradientBrushHorizontalDark = Brush.horizontalGradient(
    colors = listOf(Color(0xFF240256), Color(0xFF4401A6))
)
val gradientBrushLinearDark = Brush.linearGradient(
    colors = listOf(Color(0xFF240256), Color(0xFF4401A6))
)


fun NavGraphBuilder.addScreenWithTransitions(
    enterDuration: Int = 700,
    exitDuration: Int = 700,
    popEnterDuration: Int = 700,
    popExitDuration: Int = 700,
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    contentBack: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(enterDuration)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(exitDuration)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(popEnterDuration)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(popExitDuration)
            )
        },
        content = contentBack
    )
}

fun Context.isOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}

fun Context.showToast(mess: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, mess, duration).show()
}

fun showLog(mess: String) {
    Log.e("Mehran_TAG", "OR : $mess")
}
