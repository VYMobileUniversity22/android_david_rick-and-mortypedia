package com.example.rickandmorty.character.data.api

import com.example.rickandmorty.character.data.model.CharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CharactersService {

        @GET("character/")
        suspend fun getAllCharactersList(@Query("page") page: Int = 1): Response<CharactersDto?>

        /*@GET("character/{character_id}")
        suspend fun getSingleCharacter(@Query("character_id") id: Int = 1): Response<CharactersDto?>*/

}
