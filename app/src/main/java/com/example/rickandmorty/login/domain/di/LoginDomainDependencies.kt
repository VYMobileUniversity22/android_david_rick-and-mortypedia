package com.example.rickandmorty.login.domain.di

import com.example.rickandmorty.login.domain.LoginDomainLayerContract
import com.example.rickandmorty.login.domain.model.LoginUser
import com.example.rickandmorty.login.domain.usecase.LoginUserWithEmailAndKeypassUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginDomainDependencies {

    @Provides
    fun providesLoginUser(usecase: LoginUserWithEmailAndKeypassUc) : @JvmSuppressWildcards LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser> = usecase

}