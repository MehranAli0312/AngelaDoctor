package com.example.angeladoctor.ui.clean.presentation.disease

import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.angeladoctor.R
import com.example.angeladoctor.ui.clean.data.model.ClassName
import com.example.angeladoctor.ui.clean.data.model.Drug
import com.example.angeladoctor.ui.clean.data.model.MedicationsClass
import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import com.example.angeladoctor.ui.clean.data.remote.UiState
import com.example.angeladoctor.ui.clean.presentation.common.AppBar
import com.example.angeladoctor.ui.clean.presentation.common.ErrorDialog
import com.example.angeladoctor.ui.clean.presentation.common.MediumTitleText
import com.example.angeladoctor.ui.clean.presentation.common.ShimmerMedicineList
import com.example.angeladoctor.ui.clean.presentation.common.SmallTitleText
import com.example.angeladoctor.ui.clean.presentation.common.TitleText
import com.example.angeladoctor.ui.clean.presentation.common.bounceClick
import com.example.angeladoctor.ui.clean.presentation.common.getActivity
import com.example.angeladoctor.ui.clean.presentation.login.UserViewModel
import com.example.angeladoctor.ui.topLevel.navigations.NavRoute
import com.example.angeladoctor.ui.topLevel.utils.Constant.getGreetingMessage
import com.google.gson.Gson
import kotlin.reflect.full.memberProperties

@Composable
fun DiseaseScreen(
    navController: NavHostController,
    diseaseViewModel: DiseaseViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val activity = getActivity()

    val userName by userViewModel.userName.collectAsState()
    val diseaseState by diseaseViewModel.diseaseState.collectAsState()

//    effect
    // Animate able scale value
    val scale = remember { Animatable(0.8f) }

    // Launch animation effect
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Scaffold(topBar = {
        AppBar(text = "Medicine", navItem = {}, menuItems = {
            Box(modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.secondary, shape = CircleShape
                )
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .bounceClick {
                    userViewModel.saveUserName("")
                    activity?.finishAffinity()
                }
            ) {
                SmallTitleText(
                    text = stringResource(R.string.logOut),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
                    .scale(scale.value),
                shape = MaterialTheme.shapes.small,
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                TitleText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp, 16.dp, 10.dp),
                    text = getGreetingMessage(), color = Color.Unspecified
                )
                MediumTitleText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = userName, color = Color.Unspecified
                )
                Spacer(Modifier.height(20.dp))
            }

            when (diseaseState) {
                is UiState.Loading -> {
                    ShimmerMedicineList()
                }

                is UiState.Success -> {
                    val response = (diseaseState as UiState.Success<ResponseModel>).data
                    val drugList = extractDrugList(response)

                    MedicineList(drugList) { drug ->
                        val jsonString = Uri.encode(Gson().toJson(drug)) // Encode recipe as JSON
                        navController.navigate("${NavRoute.DiseaseDetailsScreen.route}/$jsonString")
                    }
                }

                is UiState.Error -> {
                    val errorMessage = (diseaseState as UiState.Error).message
                    ErrorDialog(message = errorMessage) {}
                }
            }
        }
    })

}

fun extractDrugList(response: ResponseModel): List<Drug> {
    return response.problems.flatMap { problem ->
        problem.Diabetes.flatMap { diabetes ->
            diabetes.medications.flatMap { medication ->
                medication.medicationsClasses.flatMap { medClass ->
                    MedicationsClass::class.memberProperties.flatMap { property ->
                        @Suppress("UNCHECKED_CAST")
                        val classList = property.get(medClass) as? List<ClassName> ?: emptyList()
                        classList.flatMap { className ->
                            ClassName::class.memberProperties.flatMap { classProperty ->
                                @Suppress("UNCHECKED_CAST")
                                classProperty.get(className) as? List<Drug> ?: emptyList()
                            }
                        }
                    }
                }
            }
        }
    }
}

