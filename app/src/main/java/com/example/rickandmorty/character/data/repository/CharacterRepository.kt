package com.example.rickandmorty.character.data.repository

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.utils.toCharacters
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.model.Character
import javax.inject.Singleton

@Singleton
object RickAndMortyCharacterRepository : DomainLayerContract.DataLayer.CharacterRepository {

    lateinit var charactersDataSource: CharactersDataSource

    override suspend fun getAllCharacterList(): Result<Characters> {
        val result = charactersDataSource.getAllCharactersListResponse()

        return result.map { dto ->
            dto?.toCharacters() ?: Characters(results = emptyList())
        }
    }


    override suspend fun getAllCharacterListByPage(page: Int): Result<Characters> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        TODO("Not yet implemented")
    }


    override suspend fun getMultipleCharactersById(ids: List<Int>): Result<Characters> {
        TODO("Not yet implemented")
    }

}

