package com.example.rickandmorty.character.domain

import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Characters

interface CharactersDomainLayerContract {
    interface PresentationLayer {
        interface UseCase<T> {

            //suspend fun getAllCharacters(): Result<Characters>
            //block: () -> Result<T>
            suspend operator fun invoke(): Result<T>

        }
    }

    interface DataLayer {

        interface CharacterRepository {

            suspend fun getAllCharacterList(): Result<Characters>

            suspend fun getCharactersNextPage(): Result<Characters>

            suspend fun getAllCharacterListByPage(page: Int): Result<Characters>

            suspend fun getCharacterById(id: Int): Result<Character>

            suspend fun getMultipleCharactersById(ids: List<Int>): Result<Characters>


        }
    }
}