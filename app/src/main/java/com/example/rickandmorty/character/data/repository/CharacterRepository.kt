package com.example.rickandmorty.character.data.repository

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.utils.toCharacters
import com.example.rickandmorty.character.data.utils.toCharactersEntity
import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton


@Singleton
object RickAndMortyCharacterRepository :
    CharactersDomainLayerContract.DataLayer.CharacterRepository {

    private var nextPage: Int = 1
    lateinit var charactersRemoteDataSource: CharactersDataSource.Remote
    lateinit var charactersLocalDataSource: CharactersDataSource.Local


    override suspend fun getAllCharacterList(): Result<Characters> =
        try {
            charactersRemoteDataSource.getAllCharactersListResponse().map { dto ->
                dto?.toCharacters()?.also {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity(page = nextPage))
                        nextPage++
                    }
                } ?: charactersLocalDataSource.fetchCharacterList().toCharacters()
            }
        } catch (e: Exception) {
            Result.success(charactersLocalDataSource.fetchCharacterList().toCharacters())
        }


    override suspend fun getCharactersNextPage(): Result<Characters> =
        try {
            charactersRemoteDataSource.getCharactersNextPage(page = nextPage).map { dto ->
                dto?.toCharacters()?.also {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity(page = nextPage))
                        nextPage++
                    }
                } ?:run {
                    withContext(Dispatchers.IO) {
                    charactersLocalDataSource.fetchCharacterNextPage(page = nextPage).toCharacters()
                        .also { if (it.results.isNotEmpty()) { nextPage++}
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Result.success(
                withContext(Dispatchers.IO){
                    charactersLocalDataSource.fetchCharacterNextPage(page = nextPage).toCharacters()
                        .also { if (it.results.isNotEmpty()) { nextPage++}
                        }
                }
            )
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

