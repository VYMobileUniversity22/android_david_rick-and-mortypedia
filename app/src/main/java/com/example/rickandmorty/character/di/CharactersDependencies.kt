package com.example.rickandmorty.character.di

import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.character.data.di.CharactersDataModule
import com.example.rickandmorty.character.domain.di.CharactersDomainDependenciesModule
import com.example.rickandmorty.character.presentation.di.CharactersPresentationModule
import dagger.Module
import dagger.Subcomponent

interface CharactersComponentFactoryProvider {
    fun provideCharactersComponentFactory(): CharactersComponent.Factory
}

@Module(subcomponents = [CharactersComponent::class])
object CharactersModule //Se le pone object delante por que no tiene datos ni constructor , es singleton

@Subcomponent(modules = [
    CharactersPresentationModule::class,
    CharactersDomainDependenciesModule::class,
    CharactersDataModule::class
])
interface CharactersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            presentationModule: CharactersPresentationModule,
            dataModule: CharactersDataModule
        ): CharactersComponent
    }

    fun inject(activity: MainActivity)
}