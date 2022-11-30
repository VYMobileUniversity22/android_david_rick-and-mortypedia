package com.example.rickandmorty.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class CharactersViewModel @Inject constructor(
    @Named("get_all_characters") val getAllCharactersUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>,
    @Named("get_characters_next_page") val getCharactersNextPageUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>
) : ViewModel() {

    val characters: StateFlow<Characters?>
        get() = _characters.asStateFlow()

    private var _characters: MutableStateFlow<Characters?> = MutableStateFlow(null)

    init {
        fetchCharactersData()
    }

    private fun fetchCharactersData() {
        viewModelScope.launch {
            getAllCharactersUc().onSuccess { characters ->
                _characters.update { characters }
            }.onFailure { ch ->
                ch.printStackTrace()
            }
        }
    }

    fun onEndOfScrollReached(){
      viewModelScope.launch {
            getCharactersNextPageUc().onSuccess { characters ->
                _characters.value = characters
            }.onFailure { ch ->
                ch.printStackTrace()
            }
        }
    }

}