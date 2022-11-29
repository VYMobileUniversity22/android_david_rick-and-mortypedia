package com.example.rickandmorty.character.data.di

import com.example.rickandmorty.character.data.datasource.CharactersDataSource
import com.example.rickandmorty.character.data.datasource.RickAndMortyCharacterDataSource
import com.example.rickandmorty.character.data.repository.RickAndMortyCharacterRepository
import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import com.example.rickandmorty.character.domain.usecase.GetAllCharactersUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CharactersDataModule {

    @Provides
    fun provideCharacterRemoteRepository(
        characterDataSource: RickAndMortyCharacterDataSource,
        localDataSource: RickAndMortyCharacterDataSource
    ): CharactersDomainLayerContract.DataLayer.CharacterRepository =
        RickAndMortyCharacterRepository.apply {
            charactersRemoteDataSource = characterDataSource
            charactersLocalDataSource = localDataSource
        }

    @Provides
    fun providesCharactersRemoteDataSource(rickAndMortyDataSource:RickAndMortyCharacterDataSource) : CharactersDataSource.Remote =
        rickAndMortyDataSource

    @Provides
    fun providesCharactersLocalDataSource(rickAndMortyDataSource: RickAndMortyCharacterDataSource) : CharactersDataSource.Local =
        rickAndMortyDataSource
    }