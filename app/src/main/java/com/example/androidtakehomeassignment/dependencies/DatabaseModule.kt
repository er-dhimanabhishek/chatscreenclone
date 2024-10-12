package com.example.androidtakehomeassignment.dependencies

import android.content.Context
import androidx.room.Room
import com.example.androidtakehomeassignment.room.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): ChatDatabase{
        return Room.databaseBuilder(context, ChatDatabase::class.java, "chatdb").build()
    }

}