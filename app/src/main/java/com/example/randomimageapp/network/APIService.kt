package com.example.randomimageapp.network

import com.example.randomimageapp.model.MarsPhoto
//import com.example.randomimageapp.model.NLPhoto
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/*
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()*/

interface  MarsAPIService{
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}

/*object MarsAPI{
    val retrofitService: MarsAPIService by lazy{
        retrofit.create(MarsAPIService::class.java)
    }
}*/
