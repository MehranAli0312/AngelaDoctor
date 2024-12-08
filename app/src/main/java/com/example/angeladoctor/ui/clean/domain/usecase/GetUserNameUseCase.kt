package com.example.angeladoctor.ui.clean.domain.usecase


import com.example.angeladoctor.ui.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(): Flow<String> = userRepository.getUserName()
}
