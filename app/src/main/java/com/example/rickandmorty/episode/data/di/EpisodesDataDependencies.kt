package com.example.rickandmorty.episode.data.di

import com.example.rickandmorty.episode.data.repository.RickAndMortyEpisodeRepository
import com.example.rickandmorty.episode.datasource.EpisodesDataSource
import com.example.rickandmorty.episode.datasource.RickAndMortyEpisodeDataSource
import com.example.rickandmorty.episode.domain.EpisodesDomainLayerContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class EpisodesDataModule {

    @Provides
    fun providesEpisodeRepository(
        remoteDataSource: EpisodesDataSource.Remote,
        localDataSource: EpisodesDataSource.Local
    ): EpisodesDomainLayerContract.DataLayer.EpisodeRepository = RickAndMortyEpisodeRepository.apply {
        episodesRemoteDataSource = remoteDataSource
        episodesLocalDataSource = localDataSource
    }

    @Provides
    fun providesEpisodesRemoteDataSource(rickAndMortyDataSource: RickAndMortyEpisodeDataSource) : EpisodesDataSource.Remote =
        rickAndMortyDataSource

    @Provides
    fun providesEpisodesLocalDataSource(rickAndMortyDataSource: RickAndMortyEpisodeDataSource) : EpisodesDataSource.Local =
        rickAndMortyDataSource

}