package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.character.di.CharactersComponent
import com.example.rickandmorty.character.di.CharactersComponentFactoryProvider
import com.example.rickandmorty.common.di.ApplicationComponent
import com.example.rickandmorty.common.di.DaggerApplicationComponent

class RickAndMortyApplication : Application(), CharactersComponentFactoryProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

    override fun provideCharactersComponentFactory(): CharactersComponent.Factory = appComponent.charactersComponentFactory()
}