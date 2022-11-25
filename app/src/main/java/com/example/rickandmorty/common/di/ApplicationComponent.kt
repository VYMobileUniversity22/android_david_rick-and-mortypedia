package com.example.rickandmorty.common.di

import com.example.rickandmorty.character.di.CharactersComponent
import com.example.rickandmorty.character.di.CharactersModule
import dagger.Component

@Component(
    modules = [CharactersModule::class, UtilsModule::class]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            utilsModule: UtilsModule
        ): ApplicationComponent
    }
    fun charactersComponentFactory(): CharactersComponent.Factory
}