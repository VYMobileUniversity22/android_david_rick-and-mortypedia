package com.example.rickandmorty.character.domain.usecase

import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.data.repository.RickAndMortyCharacterRepository
import com.example.rickandmorty.character.domain.DomainLayerContract
import javax.inject.Inject

class GetAllCharactersUc @Inject constructor(
    private val characterRepository: DomainLayerContract.DataLayer.CharacterRepository
): DomainLayerContract.PresentationLayer.UseCase {

    //val characterRepository: DomainLayerContract.DataLayer.CharacterRepository by lazy { RickAndMortyCharacterRepository }

    override suspend fun getAllCharacters(): Result<Characters> = characterRepository.getAllCharacterList()

}