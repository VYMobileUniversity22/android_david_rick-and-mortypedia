package com.example.rickandmorty.character.data.repository

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.db.CharacterEntity
import com.example.rickandmorty.character.data.model.*
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.exceptions.base.MockitoException

private const val DEFAULT_INT_VALUE = 0
private const val DEFAULT_STRING_VALUE = ""

class RickAndMortyCharacterRepositoryTest {
    lateinit var sut: RickAndMortyCharacterRepository

    @Before
    fun setUp() {

        sut = RickAndMortyCharacterRepository.apply {
        charactersRemoteDataSource = mock(CharactersDataSource.Remote::class.java)
            charactersLocalDataSource = mock(CharactersDataSource.Local::class.java)
        }

    }


    @Test
    fun `When all characters are requested, and the API response is successful but 'null', the database is queried`() = runTest{

        //given
        `when`(sut.charactersRemoteDataSource.getAllCharactersListResponse()).thenReturn(getDummyCharactersDtoNullResult())
        `when`(sut.charactersLocalDataSource.fetchCharacterList()).thenReturn(anyList())
        //when
        val result: Result<Characters> = sut.getAllCharacterList()
        //then
        //Assert.assertTrue(result.isSuccess && result.getOrNull() == null)
        verify(sut.charactersLocalDataSource).fetchCharacterList()

    }

    @Test
    fun `When all characters are requested, and the API response is succesful, a list of 'Characters' is returned`() = runTest {
        //given
        `when`(sut.charactersRemoteDataSource.getAllCharactersListResponse()).thenReturn(getDummyCharactersDtoResult())
        //when
        val result: Result<Characters> = sut.getAllCharacterList()
        //then
        //state test
        Assert.assertTrue(result.isSuccess && result.getOrNull()?.results?.isNotEmpty() == true)
        //Behaviour test
        verify(RickAndMortyCharacterRepository.charactersLocalDataSource).saveCharacterList(list = anyList())
    }

    @Test
    fun`When all characters are requested, and the API response triggers an 'Exception', the database is queried`() = runTest {
        //given
        `when`(sut.charactersRemoteDataSource.getAllCharactersListResponse()).thenThrow(MockitoException::class.java)
        `when`(sut.charactersLocalDataSource.fetchCharacterList()).thenReturn(anyList())
        //when
        val result: Result<Characters> = sut.getAllCharacterList()
        //then
        verify(sut.charactersLocalDataSource).fetchCharacterList()
    }

    private fun getDummyCharactersDtoNullResult(): Result<CharactersDto?> =
        Result.success(null)

    private fun getDummyCharactersDtoResult(): Result<CharactersDto?> =
        Result.success(getDummyCharactersDto())
    private fun getDummyCharactersDto(): CharactersDto =
        CharactersDto(
            info = getDummyInfoDto(),
            results = getDummyCharacterDtoList()
        )
    private fun getDummyCharacterDtoList(): List<CharacterDto> = listOf(getDummyCharacterDto())
    private fun getDummyCharacterDto(): CharacterDto =
        CharacterDto(
            id = DEFAULT_INT_VALUE,
            name = DEFAULT_STRING_VALUE,
            status = DEFAULT_STRING_VALUE,
            species = DEFAULT_STRING_VALUE,
            type = DEFAULT_STRING_VALUE,
            gender = DEFAULT_STRING_VALUE,
            origin = getDummyOriginDto(),
            location = getDummyLocationDto(),
            image = DEFAULT_STRING_VALUE,
            episode = emptyList(),
            url = DEFAULT_STRING_VALUE,
            created = DEFAULT_STRING_VALUE
        )
    private fun getDummyOriginDto(): OriginDto =
        OriginDto(
            name = DEFAULT_STRING_VALUE,
            url = DEFAULT_STRING_VALUE
        )
    private fun getDummyLocationDto(): LocationDto =
        LocationDto(
            name = DEFAULT_STRING_VALUE,
            url = DEFAULT_STRING_VALUE
        )

    private fun getDummyInfoDto(): InfoDto =
        InfoDto(
            count = DEFAULT_INT_VALUE,
            pages = DEFAULT_INT_VALUE,
            next = DEFAULT_STRING_VALUE,
            prev = DEFAULT_STRING_VALUE
        )
}


