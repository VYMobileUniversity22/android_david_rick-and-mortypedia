package com.example.rickandmorty.character.data.datasource

import androidx.room.RoomDatabase
import com.example.rickandmorty.character.data.api.CharactersService
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
        fun saveData(dto: CharactersDto)

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

    override fun saveData(dto: CharactersDto) {
        val testEntity: TestEntity = with(dto) {
            TestEntity(info.count.toString(),results.toString())
        }
        roomDatabaseInstance.testDao().insertAll(testEntity)
    }
}