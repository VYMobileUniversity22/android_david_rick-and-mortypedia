package com.example.rickandmorty.character.domain

import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Characters
import dagger.Provides

interface DomainLayerContract {
    interface PresentationLayer {
        interface UseCase {
            suspend fun getAllCharacters(): Result<Characters>
        }
    }

    interface DataLayer {

        interface CharacterRepository {

            suspend fun getAllCharacterList(): Result<Characters>

            suspend fun getAllCharacterListByPage(page: Int): Result<Characters>

            suspend fun getCharacterById(id: Int): Result<Character>

            suspend fun getMultipleCharactersById(ids: List<Int>): Result<Characters>

        }
    }
}