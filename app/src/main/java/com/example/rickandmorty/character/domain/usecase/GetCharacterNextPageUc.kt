package com.example.rickandmorty.character.domain.usecase

import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import javax.inject.Inject

class GetCharacterNextPageUc @Inject constructor(
    private val characterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository
) : CharactersDomainLayerContract.PresentationLayer.UseCase <Characters> {
    override suspend fun invoke(): Result<Characters> = characterRepository.getAllCharacterList()
}