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
    }

    interface Local {
        suspend fun saveCharacterList(list: List<CharacterEntity>)
        suspend fun fetchCharacterList(): List<CharacterEntity>

    }

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : CharactersDataSource.Remote, CharactersDataSource.Local {
    //private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList()
            .runCatching { body() }



    override suspend fun saveCharacterList(list: List<CharacterEntity>) {
        /*val testEntity: TestEntity = with(list) {
            TestEntity(info = info.count.toString(), results = results.toString())
        }
        roomDatabaseInstance.testDao().insertAll(testEntity)
    }*/
        roomDatabaseInstance.characterDao().insertAll(*list.toTypedArray())
    }

    override suspend fun fetchCharacterList(): List<CharacterEntity> = roomDatabaseInstance.characterDao().getAll()
}