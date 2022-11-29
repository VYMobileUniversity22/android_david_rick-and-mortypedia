package com.example.rickandmorty.episode.domain.usecase

import com.example.rickandmorty.episode.domain.EpisodesDomainLayerContract
import com.example.rickandmorty.episode.domain.model.Episodes
import javax.inject.Inject

class GetAllEpisodesUc @Inject constructor(
    private val episodeRepository: EpisodesDomainLayerContract.DataLayer.EpisodeRepository
) : EpisodesDomainLayerContract.PresentationLayer.UseCase {

    override suspend fun getAllEpisodes(): Result<Episodes> = episodeRepository.getAllEpisodesList()

}