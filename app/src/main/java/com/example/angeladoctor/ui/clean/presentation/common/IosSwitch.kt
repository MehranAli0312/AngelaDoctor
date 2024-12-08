package com.example.angeladoctor.ui.clean.presentation.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IosSwitch(
    switchOnCurrent: MutableState<Boolean>,
    modifier:Modifier = Modifier,
    width: Dp = 45.dp,
    height: Dp = 27.dp,
    checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedTrackColor: Color = MaterialTheme.colorScheme.secondary,
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 2.dp,
    thumbSize: Dp = 22.dp, callback: (switch: Boolean) -> Unit
) {


    // state of the switch

    // for moving the thumb
    val alignment by animateAlignmentAsState(if (switchOnCurrent.value) 1f else -1f)

    // outer rectangle with border
    Box(
        modifier = modifier
            .size(width = width, height = height)
            .background(
                color = if (switchOnCurrent.value) checkedTrackColor else uncheckedTrackColor,
                shape = CircleShape
            )
            .click {
                switchOnCurrent.value = !switchOnCurrent.value
                callback(switchOnCurrent.value)
            },
        contentAlignment = Alignment.Center
    ) {

        // this is to add padding at the each horizontal side
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {

            // thumb with icon
            Box(
                modifier = Modifier
                    .size(size = thumbSize)
                    .background(
                        color = White,
                        shape = CircleShape
                    )
                    .padding(all = iconInnerPadding),
            )
        }
    }


}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}