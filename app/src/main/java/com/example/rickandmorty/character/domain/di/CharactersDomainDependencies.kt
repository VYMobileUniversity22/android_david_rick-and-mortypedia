package com.example.rickandmorty.character.domain.di

import com.example.rickandmorty.character.data.datasource.RickAndMortyCharacterDataSource
import com.example.rickandmorty.character.data.repository.RickAndMortyCharacterRepository
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.usecase.GetAllCharactersUc
import dagger.Module
import dagger.Provides

@Module
class CharactersDomainDependenciesModule() {

    @Provides
    fun providesUseCase(getAllCharactersUc: GetAllCharactersUc): DomainLayerContract.PresentationLayer.UseCase =
        getAllCharactersUc

    @Provides
    fun provideCharacterRepository(characterDataSource: RickAndMortyCharacterDataSource): DomainLayerContract.DataLayer.CharacterRepository =
        RickAndMortyCharacterRepository.apply {
            charactersDataSource = characterDataSource
        }
}