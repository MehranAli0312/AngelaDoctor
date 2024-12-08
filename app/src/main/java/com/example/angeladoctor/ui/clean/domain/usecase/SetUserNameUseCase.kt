package com.example.angeladoctor.ui.clean.domain.usecase


import com.example.angeladoctor.ui.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetUserNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(name: String) = userRepository.setUserName(name)
}