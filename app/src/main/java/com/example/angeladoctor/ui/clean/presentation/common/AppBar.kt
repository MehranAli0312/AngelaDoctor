package com.example.angeladoctor.ui.clean.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    text: String,
    modifier: Modifier = Modifier,
    navItem: (@Composable () -> Unit)? = null,
    menuItems: (@Composable () -> Unit)? = null
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier

            )
        },
        navigationIcon = {
            if (navItem != null) {
                Row {
                    Spacer(modifier = Modifier.width(16.dp))
                    navItem()
                }
            }
        },
        actions = {
            if (menuItems != null) {
                Row {
                    menuItems()
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        })
}