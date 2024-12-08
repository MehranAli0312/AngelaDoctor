package com.example.angeladoctor.ui.clean.data.remote

import com.example.angeladoctor.ui.clean.data.model.ResponseModel
import retrofit2.http.GET

interface ApiService {
    @GET("ab3ab2d4-87c2-4495-8164-86664b1195ec")
    suspend fun fetchProblems(): ResponseModel
}