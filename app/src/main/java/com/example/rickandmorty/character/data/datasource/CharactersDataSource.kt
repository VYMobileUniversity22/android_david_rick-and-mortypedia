package com.example.rickandmorty.character.data.datasource

import com.example.rickandmorty.character.data.api.CharactersService
import com.example.rickandmorty.character.data.db.CharacterEntity
import com.example.rickandmorty.character.data.db.TestEntity
import com.example.rickandmorty.character.data.model.CharactersDto
import com.example.rickandmorty.common.db.ApplicationDatabase
import retrofit2.Retrofit
import javax.inject.Inject

interface CharactersDataSource {

    interface Remote {
        suspend fun getAllCharactersListResponse(): Result<CharactersDto?>
        suspend fun getCharactersNextPage(page: Int): Result<CharactersDto?>

    }

    interface Local {
        suspend fun saveCharacterList(list: List<CharacterEntity>)
        suspend fun fetchCharacterList(): List<CharacterEntity>
        suspend fun fetchCharacterNextPage(page:Int): List<CharacterEntity>

    }

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : CharactersDataSource.Remote, CharactersDataSource.Local {

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList()
            .runCatching { body() }

    override suspend fun getCharactersNextPage(page: Int): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList(page = page)
            .runCatching { body() }



    override suspend fun saveCharacterList(list: List<CharacterEntity>) {

        roomDatabaseInstance.characterDao().insertAll(*list.toTypedArray())
    }

    override suspend fun fetchCharacterList(): List<CharacterEntity> = roomDatabaseInstance.characterDao().getAll()

    override suspend fun fetchCharacterNextPage(page: Int): List<CharacterEntity> =
        roomDatabaseInstance.characterDao().getCharactersByPage(page = page)

}