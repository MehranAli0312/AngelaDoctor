package com.example.angeladoctor.ui.clean.presentation.disease

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import com.example.angeladoctor.ui.clean.data.remote.UiState
import com.example.angeladoctor.ui.clean.domain.usecase.DiseaseUseCase
import com.example.angeladoctor.ui.clean.presentation.common.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(private val diseaseUseCase: DiseaseUseCase) :
    ViewModel() {


    private val _diseaseState = MutableStateFlow<UiState<ResponseModel>>(UiState.Loading)
    val diseaseState: StateFlow<UiState<ResponseModel>> = _diseaseState

    init {
        getProblems()
    }

    fun getProblems() {
        viewModelScope.launch {
            diseaseUseCase.invoke()
                .collect { state ->
                    showLog("DiseaseViewModel, Emitting state: $state")
                    _diseaseState.value = state
                }
        }
    }

}