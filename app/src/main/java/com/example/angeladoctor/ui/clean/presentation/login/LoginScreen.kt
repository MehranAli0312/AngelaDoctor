package com.example.angeladoctor.ui.clean.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.angeladoctor.R
import com.example.angeladoctor.ui.clean.presentation.common.PasswordTextField

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    // State variables to store user input
    val userName = remember {
        mutableStateOf("")
    }
    val userPassword = remember {
        mutableStateOf("")
    }

    // Column to arrange UI elements vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {

        // Welcome message
        Text(
            text = stringResource(R.string.loginTitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp)
        )

        // Username input field
        OutlinedTextField(
            value = userName.value, onValueChange = {
                userName.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "username")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            value = userPassword.value,
            onValueChange = { userPassword.value = it },
            label = stringResource(id = R.string.login_password_label),
            isError = false,
            errorText = "",
            imeAction = ImeAction.Done
        )
        OutlinedTextField(
            value = userPassword.value, onValueChange = {
                userPassword.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "password")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )


        // Login button
        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}