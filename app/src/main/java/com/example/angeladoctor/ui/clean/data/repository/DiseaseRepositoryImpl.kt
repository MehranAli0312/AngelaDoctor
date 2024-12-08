package com.example.angeladoctor.ui.clean.data.repository

import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import com.example.angeladoctor.ui.clean.data.remote.ApiService
import com.example.angeladoctor.ui.clean.data.remote.UiState
import com.example.angeladoctor.ui.clean.domain.repository.DiseaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiseaseRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    DiseaseRepository {

    override suspend fun fetchProblems(): Flow<UiState<ResponseModel>> = flow {
        emit(UiState.Loading) // Emit loading state
        try {
            val response = apiService.fetchProblems()
            emit(UiState.Success(response)) // Emit success state
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error occurred")) // Emit error state
        }
    }.catch { exception ->
        emit(UiState.Error(exception.message ?: "Error fetching data"))
    }.flowOn(Dispatchers.IO) // Perform operations on IO thread
}