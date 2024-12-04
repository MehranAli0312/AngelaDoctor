package com.example.angeladoctor.ui.clean.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.angeladoctor.R
import com.example.objectremovercompose.ui.navigations.NavRoute
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController
) {

    val alpha = remember {
        Animatable(0f)
    }

    var startAnimation by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else (-200).dp,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }


    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f, animationSpec = tween(2500)
        )

        val screenDuration = 10000L
        delay(screenDuration)

//        navController.popBackStack()
//        navController.navigate(NavRoute.HomeScreen.route)

    }

    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp

    // Desired percentage for the gradient to end
    val gradientEndPercentage = 0.9f
    val gradientEndYPx =
        with(LocalDensity.current) { (screenHeightDp * gradientEndPercentage).toPx() }


    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0x996FF5D6),
            Color(0x806FF5D6),
            Color(0x666FF5D6),
            Color(0x4D6FF5D6),
            Color(0x336FF5D6),
            Color(0x1A6FF5D6),
            Color(0x006FF5D6)
        ), startY = 0f, endY = gradientEndYPx
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(gradientEndYPx.dp)
            .background(brush = gradientBrush)
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(130.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(x = offsetX)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                )
            )
        }
    }

}

