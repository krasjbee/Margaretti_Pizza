package com.example.margarettipizza.data.remote

import com.example.margarettipizza.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val gson = Gson().newBuilder().create()

    private val gsonConverter = GsonConverterFactory.create(gson)

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val callAdapterFactory = RxJava3CallAdapterFactory.create()

    private const val BASE_URL = "https://springboot-kotlin-demo.herokuapp.com/"

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverter)
        .addCallAdapterFactory(callAdapterFactory)
        .build()
        .create(PizzaRemoteApi::class.java)
}