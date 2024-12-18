package com.example.tampilinternet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. Definisikan endpoint API
interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

// 2. Inisialisasi Retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}