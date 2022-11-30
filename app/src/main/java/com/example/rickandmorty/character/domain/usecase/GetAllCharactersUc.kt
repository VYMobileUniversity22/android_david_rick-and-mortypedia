package com.example.rickandmorty.character.domain.usecase

import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import javax.inject.Inject

class GetAllCharactersUc @Inject constructor(
    private val characterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository
): CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> {
    //val characterRepository: DomainLayerContract.DataLayer.CharacterRepository by lazy { RickAndMortyCharacterRepository }
    //override suspend fun getAllCharacters(): Result<Characters> = characterRepository.getAllCharacterList()
        override suspend fun invoke(): Result<Characters> = characterRepository.getAllCharacterList()

}