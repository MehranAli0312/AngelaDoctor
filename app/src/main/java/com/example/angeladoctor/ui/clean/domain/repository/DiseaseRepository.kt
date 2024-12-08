package com.example.angeladoctor.ui.clean.domain.repository

import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import com.example.angeladoctor.ui.clean.data.remote.UiState
import kotlinx.coroutines.flow.Flow

interface DiseaseRepository {
    suspend fun fetchProblems(): Flow<UiState<ResponseModel>>
}