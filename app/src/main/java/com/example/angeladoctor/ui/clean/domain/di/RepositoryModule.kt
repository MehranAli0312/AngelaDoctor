package com.example.angeladoctor.ui.clean.domain.di

import com.example.angeladoctor.ui.clean.data.repository.DiseaseRepositoryImpl
import com.example.angeladoctor.ui.clean.data.repository.UserRepositoryImpl
import com.example.angeladoctor.ui.clean.domain.repository.DiseaseRepository
import com.example.angeladoctor.ui.clean.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideCurrentWeatherRepo(diseaseRepositoryImpl: DiseaseRepositoryImpl): DiseaseRepository

    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}