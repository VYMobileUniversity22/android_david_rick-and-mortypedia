package com.example.rickandmorty.episode.data.repository

import com.example.rickandmorty.episode.datasource.EpisodesDataSource
import com.example.rickandmorty.episode.domain.EpisodesDomainLayerContract
import com.example.rickandmorty.episode.domain.model.Episode
import com.example.rickandmorty.episode.domain.model.Episodes
import com.example.rickandmorty.episode.utils.toEpisodes
import com.example.rickandmorty.episode.utils.toEpisodesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RickAndMortyEpisodeRepository: EpisodesDomainLayerContract.DataLayer.EpisodeRepository {

    lateinit var episodesRemoteDataSource: EpisodesDataSource.Remote
    lateinit var episodesLocalDataSource: EpisodesDataSource.Local

    override suspend fun getAllEpisodesList(): Result<Episodes> =
        try {
            episodesRemoteDataSource.getAllEpisodesListResponse().map { dto ->
                dto?.toEpisodes()?.also {
                    withContext(Dispatchers.IO) {
                        episodesLocalDataSource.saveEpisodeList(list = dto.toEpisodesEntity())
                    }
                } ?: episodesLocalDataSource.fetchEpisodeList().toEpisodes()
            }
        } catch (e: Exception) {
            Result.success(episodesLocalDataSource.fetchEpisodeList().toEpisodes())
        }

    override suspend fun getAllEpisodesListByPage(page: Int): Episodes {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodeById(id: Int): Episode {
        TODO("Not yet implemented")
    }

    override suspend fun getMultipleEpisodesById(ids: List<Int>): Episodes {
        TODO("Not yet implemented")
    }

}