package com.example.rickandmorty.common.di

import com.example.rickandmorty.character.di.CharactersComponent
import com.example.rickandmorty.character.di.CharactersModule
import dagger.Component

@Component(
    modules = [CharactersModule::class]
)
interface ApplicationComponent {

    fun charactersComponentFactory(): CharactersComponent.Factory
}