package com.example.rickandmorty.common.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.common.db.ApplicationDatabase
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class UtilsModule(private val applicationContext: Context) {

    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit =
        getRetrofitInstance(converterFactory = converterFactory)

    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun providesRoomDataBaseInstance() : ApplicationDatabase = Room.databaseBuilder(
        applicationContext,
        ApplicationDatabase::class.java,"rick-and-morty-db"
    ).build()
}