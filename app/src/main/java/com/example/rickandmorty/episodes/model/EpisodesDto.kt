package com.example.rickandmorty.episodes.model


data class EpisodesDto(
    val info: EpisodesInfoDto,
    val results: List<EpisodeDto>
)
data class EpisodesInfoDto(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Any
)
data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,//Also List<Characters>
    val url: String,
    val created: String
)