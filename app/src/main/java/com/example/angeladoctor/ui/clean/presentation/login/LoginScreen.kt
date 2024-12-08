package com.example.angeladoctor.ui.clean.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.angeladoctor.R
import com.example.angeladoctor.ui.clean.presentation.common.ErrorTextInputField
import com.example.angeladoctor.ui.clean.presentation.common.IosSwitch
import com.example.angeladoctor.ui.clean.presentation.common.MediumTitleText
import com.example.angeladoctor.ui.clean.presentation.common.PasswordTextField
import com.example.angeladoctor.ui.clean.presentation.common.SmallTitleText
import com.example.angeladoctor.ui.clean.presentation.common.TitleText
import com.example.angeladoctor.ui.clean.presentation.common.bounceClick
import com.example.angeladoctor.ui.clean.presentation.common.showLog
import com.example.angeladoctor.ui.clean.presentation.common.showToast
import com.example.angeladoctor.ui.topLevel.navigations.NavRoute

@Composable
fun LoginScreen(
    navController: NavHostController, userViewModel: UserViewModel = hiltViewModel()
) {

    val userName = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    val credentialSwitch = rememberSaveable { mutableStateOf(true) }

    // Error states
    val userNameError = remember { mutableStateOf(false) }
    val userPasswordError = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {

        TitleText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp), text = stringResource(R.string.loginTitle)
        )

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
            isError = userNameError.value,
            supportingText = {
                if (userNameError.value) {
                    ErrorTextInputField(text = "Name cannot be empty")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            value = userPassword.value,
            onValueChange = { userPassword.value = it },
            label = stringResource(id = R.string.login_password_label),
            isError = userPasswordError.value,
            errorText = if (userPasswordError.value) "Password cannot be empty" else "",
            imeAction = ImeAction.Done
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 5.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            SmallTitleText(
                modifier = Modifier.weight(1f), text = stringResource(R.string.credential)
            )
            IosSwitch(switchOnCurrent = credentialSwitch) {}
        }


        Box(
            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = {
                    val isUserNameEmpty = userName.value.isBlank()
                    val isUserPasswordEmpty = userPassword.value.isBlank()

                    if (isUserNameEmpty || isUserPasswordEmpty) {
                        userNameError.value = isUserNameEmpty
                        userPasswordError.value = isUserPasswordEmpty
                    } else {
                        if (credentialSwitch.value) {
                            userViewModel.saveUserName(userName.value)
                        }
                        navController.navigate(NavRoute.DiseaseScreen.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
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
}