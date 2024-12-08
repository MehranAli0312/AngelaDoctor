package com.example.angeladoctor.ui.clean.data.remote;

import retrofit2.Response;
import retrofit2.http.GET;

interface ApiService {

    @GET("")
    suspend fun getAllProductListAPI() : Response<ObjectRemoverModel>

}