package com.example.rickandmorty.character.data.datasource

import com.example.rickandmorty.character.data.api.CharactersService
import com.example.rickandmorty.character.data.model.CharactersDto
import retrofit2.Retrofit
import javax.inject.Inject

interface CharactersDataSource {

    suspend fun getAllCharactersListResponse(): Result<CharactersDto?>

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit
) : CharactersDataSource {
    //private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
            retrofitInstance.create(CharactersService::class.java).getAllCharactersList()
}