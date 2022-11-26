package com.example.rickandmorty.episodes.data.api

import retrofit2.http.GET
import retrofit2.http.Query


interface EpisodesService {
    @GET("episode/")
    suspend fun getAllEpisodesList(@Query("page") page: Int = 1)
}