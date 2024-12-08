package com.example.angeladoctor.ui.clean.domain.repository


import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserName(): Flow<String>
    suspend fun setUserName(name: String)
}