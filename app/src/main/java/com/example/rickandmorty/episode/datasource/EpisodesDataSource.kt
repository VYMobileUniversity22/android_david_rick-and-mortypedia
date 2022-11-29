package com.example.rickandmorty.episode.datasource

import com.example.rickandmorty.common.db.ApplicationDatabase
import com.example.rickandmorty.episode.data.api.EpisodesService
import com.example.rickandmorty.episode.data.db.EpisodeEntity
import com.example.rickandmorty.episode.data.model.EpisodesDto
import retrofit2.Retrofit
import javax.inject.Inject

interface EpisodesDataSource {

    interface Remote {

        suspend fun getAllEpisodesListResponse(): Result<EpisodesDto?>

    }

    interface Local {

        suspend fun saveEpisodeList(list: List<EpisodeEntity>)

        suspend fun fetchEpisodeList(): List<EpisodeEntity>

    }

}

class RickAndMortyEpisodeDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : EpisodesDataSource.Remote, EpisodesDataSource.Local {

    override suspend fun getAllEpisodesListResponse(): Result<EpisodesDto?> =
        retrofitInstance.create(EpisodesService::class.java).getAllEpisodesList().runCatching { body() }

    override suspend fun saveEpisodeList(list: List<EpisodeEntity>) {
        roomDatabaseInstance.episodeDao().insertAll(*list.toTypedArray())
    }

    override suspend fun fetchEpisodeList(): List<EpisodeEntity> =
        roomDatabaseInstance.episodeDao().getAllEpisodes()

}
