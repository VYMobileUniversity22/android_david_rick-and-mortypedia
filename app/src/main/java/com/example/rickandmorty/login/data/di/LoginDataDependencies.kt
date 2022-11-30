package com.example.rickandmorty.login.data.di

import com.example.rickandmorty.login.data.datasource.FirebaseAuthDataSource
import com.example.rickandmorty.login.data.datasource.LoginDataSource
import com.example.rickandmorty.login.data.repository.RickAndMortyLoginRepository
import com.example.rickandmorty.login.domain.LoginDomainLayerContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginDataDependencies  {

    @Provides
    fun providesEpisodeRepository(
        loginDataSource: LoginDataSource
    ): LoginDomainLayerContract.DataLayer.LoginRepository = RickAndMortyLoginRepository.apply {
        this.loginDataSource = loginDataSource
    }

    @Provides
    fun providesLoginDataSource(firebaseDataSource: FirebaseAuthDataSource) : LoginDataSource =
        firebaseDataSource
}