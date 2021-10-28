package com.example.kaficapp

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun ProvideAPI(): API {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.URL).build().create(API::class.java)
    }
    @Provides
    @Singleton
    fun ProvideContext(@ApplicationContext context: Context):Context=context
}