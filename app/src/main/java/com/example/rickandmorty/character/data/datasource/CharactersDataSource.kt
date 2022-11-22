package com.example.rickandmorty.character.data.datasource

import com.example.rickandmorty.character.data.api.CharactersService
import com.example.rickandmorty.character.data.model.CharactersDto
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CharactersDataSource {

    suspend fun getAllCaractersListResponse(): CharactersDto?

}

class RickAndMortyCharacterDataSource : CharactersDataSource {
    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }

    override suspend fun getAllCaractersListResponse(): CharactersDto? =
            retrofitInstance.create(CharactersService::class.java).getAllCharactersList()
}