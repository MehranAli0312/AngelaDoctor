package com.example.angeladoctor.ui.clean.data.repository

import com.example.angeladoctor.ui.clean.data.local.AppDataStore
import com.example.angeladoctor.ui.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : UserRepository {

    override fun getUserName(): Flow<String> = appDataStore.getUserName()

    override suspend fun setUserName(name: String) {
        appDataStore.setUserName(name)
    }
}