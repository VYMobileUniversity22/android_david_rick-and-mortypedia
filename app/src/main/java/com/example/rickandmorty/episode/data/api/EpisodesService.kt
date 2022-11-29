package com.example.rickandmorty.episode.data.api

import com.example.rickandmorty.episode.data.model.EpisodesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesService {

    @GET("episode")
    suspend fun getAllEpisodesList(@Query("page") page: Int = 1): Response<EpisodesDto?>
}