package com.example.quicksellandroidtakehomeassignment.dependencies

import com.example.quicksellandroidtakehomeassignment.ChatConstants
import com.example.quicksellandroidtakehomeassignment.retrofitapi.ChatAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(ChatConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesChatAPI(retrofit: Retrofit): ChatAPI {
        return retrofit.create(ChatAPI::class.java)
    }

}