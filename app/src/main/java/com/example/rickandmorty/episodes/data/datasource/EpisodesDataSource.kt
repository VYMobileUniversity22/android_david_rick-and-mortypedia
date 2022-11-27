package com.example.rickandmorty.episodes.data.datasource

import com.example.rickandmorty.common.db.ApplicationDatabase
import retrofit2.Retrofit
import javax.inject.Inject

interface EpisodesDataSource {
    interface Remote {
        //suspend fun getAllEpisodesListResponse()
    }
    interface Local {
        //suspend fun saveEpisodeList()
        //suspend fun fetchEpisodeList()
    }
}

class RickAndMortyEpisodesDataSource @Inject constructor(
    private val episodeRetrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : EpisodesDataSource.Remote, EpisodesDataSource.Local {

}