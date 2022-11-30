package com.example.rickandmorty.common.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.example.rickandmorty.common.db.ApplicationDatabase
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit =
        getRetrofitInstance(converterFactory = converterFactory)

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRoomDataBaseInstance(@ApplicationContext applicationContext: Context) : ApplicationDatabase = Room.databaseBuilder(
        applicationContext,
        ApplicationDatabase::class.java,"rick-and-morty-db"
    ).build()

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}