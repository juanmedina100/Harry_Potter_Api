package com.jimd.harrypotterapi.di

import com.jimd.harrypotterapi.data.ApiService
import com.jimd.harrypotterapi.data.repository.HPRepositoryImpl
import com.jimd.harrypotterapi.domain.repositori.HPRepository
import com.jimd.harrypotterapi.utils.Constants.Companion.URL_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val timeout = 10L // Tiempo de espera en segundos
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit2(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(hpRepositoryImpl: HPRepositoryImpl):HPRepository{
        return hpRepositoryImpl
    }
}
