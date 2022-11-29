package com.example.rickandmorty.character.domain.usecase

import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import javax.inject.Inject

class GetCharacterNextPageUc @Inject constructor(
    private val characterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository
){

}