package com.example.rickandmorty.character.data.repository

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.datasource.RickAndMortyCharacterDataSource
import com.example.rickandmorty.character.data.model.CharacterDto
import com.example.rickandmorty.character.data.model.CharactersDto
import com.example.rickandmorty.character.data.model.LocationDto
import com.example.rickandmorty.character.data.model.OriginDto
import com.example.rickandmorty.character.data.utils.toCharacters
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.model.Character


object RickAndMortyCharacterRepository : DomainLayerContract.DataLayer.CharacterRepository {

    private val charactersDataSource: CharactersDataSource by lazy { RickAndMortyCharacterDataSource() }

    override suspend fun getAllCharacterList(): Characters =
        charactersDataSource.getAllCaractersListResponse().toCharacters()


    override suspend fun getAllCharacterListByPage(page: Int): Characters {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: Int): Character {
        TODO("Not yet implemented")
    }


    override suspend fun getMultipleCharactersById(ids: List<Int>): Characters {
        TODO("Not yet implemented")
    }

}

