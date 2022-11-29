package com.example.rickandmorty.episode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.episode.domain.EpisodesDomainLayerContract
import com.example.rickandmorty.episode.domain.model.Episodes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    val getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
) : ViewModel() {

    val episodes: StateFlow<Episodes?> get() = _episodes.asStateFlow()

    private var _episodes: MutableStateFlow<Episodes?> = MutableStateFlow(null)

    init {
        fetchEpisodesData()
    }


    private fun fetchEpisodesData() {
        viewModelScope.launch {
            getAllEpisodesUc.getAllEpisodes().onSuccess { episodes ->
                _episodes.update { episodes }
            }.onFailure { th ->
                th.printStackTrace()
            }
        }
    }


}
