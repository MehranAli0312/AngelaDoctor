package com.example.angeladoctor.ui.clean.domain.usecase

import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import com.example.angeladoctor.ui.clean.data.remote.UiState
import com.example.angeladoctor.ui.clean.domain.repository.DiseaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiseaseUseCase @Inject constructor(private val diseaseRepository: DiseaseRepository) {
    suspend fun invoke(): Flow<UiState<ResponseModel>> = diseaseRepository.fetchProblems()
}