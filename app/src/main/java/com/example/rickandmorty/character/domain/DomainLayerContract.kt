package com.example.rickandmorty.character.domain

import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Characters

interface DomainLayerContract {

    interface PresentationLayer {
        interface UseCase {
            suspend fun getAllCharacters(): Characters
        }
    }

    interface DataLayer {

        interface CharacterRepository {

            suspend fun getAllCharacterList(): Characters

            suspend fun getAllCharacterListByPage(page: Int): Characters

            suspend fun getCharacterById(id: Int): Character

            suspend fun getMultipleCharactersById(ids: List<Int>): Characters

        }
    }
}