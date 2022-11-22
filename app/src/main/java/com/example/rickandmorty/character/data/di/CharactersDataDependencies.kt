package com.example.rickandmorty.character.data.di

import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CharactersDataModule() {

    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit =
        getRetrofitInstance(converterFactory = converterFactory)

    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()
}