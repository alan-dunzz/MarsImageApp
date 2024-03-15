package com.example.randomimageapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://api.pexels.com/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PexelAPIService{
    @GET("search?")

    suspend fun getPhotos(
        @Header("Authorization") apikey: String,
        @Query("query") query: String,
        @Query("per_page") perpage: Int=10
    ):String

}
//Patrón de diseño Singleton

object PexelApi{
    val retrofitService: PexelAPIService by lazy{
        retrofit.create(PexelAPIService::class.java)
    }
}
