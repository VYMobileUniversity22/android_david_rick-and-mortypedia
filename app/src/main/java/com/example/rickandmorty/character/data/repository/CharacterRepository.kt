package com.example.rickandmorty.character.data.repository

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.db.CharacterEntity
import com.example.rickandmorty.character.data.db.LocationEntity
import com.example.rickandmorty.character.data.db.OriginEntity
import com.example.rickandmorty.character.data.model.CharacterDto
import com.example.rickandmorty.character.data.model.CharactersDto
import com.example.rickandmorty.character.data.model.LocationDto
import com.example.rickandmorty.character.data.model.OriginDto
import com.example.rickandmorty.character.data.utils.toCharacters
import com.example.rickandmorty.character.data.utils.toCharactersEntity
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Location
import com.example.rickandmorty.character.domain.model.Origin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton


@Singleton
object RickAndMortyCharacterRepository : DomainLayerContract.DataLayer.CharacterRepository {

    lateinit var charactersRemoteDataSource: CharactersDataSource.Remote
    lateinit var charactersLocalDataSource: CharactersDataSource.Local


    override suspend fun getAllCharacterList(): Result<Characters> =
        try {
            charactersRemoteDataSource.getAllCharactersListResponse().map { dto ->
                dto?.toCharacters()?.also {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity())
                    }
                } ?: charactersLocalDataSource.fetchCharacterList().toCharacters()
            }
        } catch (e: Exception) {
            Result.success(charactersLocalDataSource.fetchCharacterList().toCharacters())
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

