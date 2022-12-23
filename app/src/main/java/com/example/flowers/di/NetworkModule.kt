package com.example.flowers.di

import com.example.flowers.data.network.FlowersAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://my-json-server.typicode.com/"
const val TIMEOUT = 10L

val networkModule = module {
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .callTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCashApi(retrofit: Retrofit): FlowersAPI = retrofit.create(FlowersAPI::class.java)

    single { provideOkHttp() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideCashApi(get()) }
}