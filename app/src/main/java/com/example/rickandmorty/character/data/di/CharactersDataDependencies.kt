package com.example.rickandmorty.character.data.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.character.data.datasource.RickAndMortyCharacterDataSource
import com.example.rickandmorty.character.data.repository.RickAndMortyCharacterRepository
import com.example.rickandmorty.character.domain.DomainLayerContract
import com.example.rickandmorty.character.domain.usecase.GetAllCharactersUc
import com.example.rickandmorty.common.db.ApplicationDatabase
import com.example.rickandmorty.data.model.utils.getRetrofitInstance
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object CharactersDataModule {
    @Provides
    fun providesUseCase(getAllCharactersUc: GetAllCharactersUc): DomainLayerContract.PresentationLayer.UseCase =
        getAllCharactersUc

    @Provides
    fun provideCharacterRemoteRepository(
        characterDataSource: RickAndMortyCharacterDataSource,
        localDataSource: RickAndMortyCharacterDataSource
    ): DomainLayerContract.DataLayer.CharacterRepository =
        RickAndMortyCharacterRepository.apply {
            charactersRemoteDataSource = characterDataSource
            charactersLocalDataSource = localDataSource
        }

}