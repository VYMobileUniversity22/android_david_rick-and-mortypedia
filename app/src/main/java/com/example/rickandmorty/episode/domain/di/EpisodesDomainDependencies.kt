package com.example.rickandmorty.episode.domain.di

import com.example.rickandmorty.episode.domain.EpisodesDomainLayerContract
import com.example.rickandmorty.episode.domain.usecase.GetAllEpisodesUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class EpisodesDomainModule {

    @Provides
    fun providesGetAllEpisodesUc(usecase: GetAllEpisodesUc) : EpisodesDomainLayerContract.PresentationLayer.UseCase = usecase
}