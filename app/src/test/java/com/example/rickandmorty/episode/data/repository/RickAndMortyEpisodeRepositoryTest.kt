package com.example.rickandmorty.episode.data.repository

import com.example.rickandmorty.character.data.model.InfoDto
import com.example.rickandmorty.character.data.model.LocationDto
import com.example.rickandmorty.character.data.model.OriginDto
import com.example.rickandmorty.character.domain.model.Origin
import com.example.rickandmorty.episode.data.model.EpisodeDto
import com.example.rickandmorty.episode.data.model.EpisodesDto
import com.example.rickandmorty.episode.datasource.EpisodesDataSource
import com.example.rickandmorty.episode.domain.model.Episodes
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

    private const val DEFAULT_INT_VALUE = 0
    private const val DEFAULT_STRING_VALUE = ""

class RickAndMortyEpisodeRepositoryTest {

    private lateinit var sut: RickAndMortyEpisodeRepository


    @Before
    fun setUp() {
        sut = RickAndMortyEpisodeRepository.apply {
           episodesRemoteDataSource = mock(EpisodesDataSource.Remote::class.java)
            episodesLocalDataSource = mock(EpisodesDataSource.Local::class.java)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When all episodes are requested, and the API response is successful, a list of 'Episodes' is returned`() = runTest {
        //given
        `when`(sut.episodesRemoteDataSource.getAllEpisodesListResponse()).thenReturn(
            getDummyEpisodesDtoResult()
        )
        //when
        val result: Result<Episodes> = sut.getAllEpisodesList()
        //then
        assertTrue(result.isSuccess && result.getOrNull()?.results?.isNotEmpty() == true)
        verify(sut.episodesLocalDataSource).saveEpisodeList(list = anyList())
    }
}
private fun getDummyEpisodesDtoNullResult(): Result<EpisodesDto?> =
    Result.success(null)

private fun getDummyEpisodesDtoResult(): Result<EpisodesDto?> =
    Result.success(getDummyEpisodesDto())

private fun getDummyEpisodesDto(): EpisodesDto =
    EpisodesDto(
        info = getDummyInfoDto(),
        results = getDummyEpisodeDtoList()
    )

private fun getDummyEpisodeDtoList(): List<EpisodeDto> = listOf(getDummyEpisodeDto())

private fun getDummyEpisodeDto(): EpisodeDto =
    EpisodeDto(
        id = DEFAULT_INT_VALUE,
        name = DEFAULT_STRING_VALUE,
        airDate = DEFAULT_STRING_VALUE,
        characters = listOf(DEFAULT_STRING_VALUE),
        created = DEFAULT_STRING_VALUE,
        episode = DEFAULT_STRING_VALUE,
        url = DEFAULT_STRING_VALUE
    )


private fun getDummyInfoDto(): InfoDto =
    InfoDto(
        count = DEFAULT_INT_VALUE,
        pages = DEFAULT_INT_VALUE,
        next = DEFAULT_STRING_VALUE,
        prev = DEFAULT_STRING_VALUE
    )