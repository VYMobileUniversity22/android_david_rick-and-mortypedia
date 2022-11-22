package com.example.rickandmorty.di

import com.example.rickandmorty.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ CharactersModules::class]
)
interface CharactersComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance mainActivity: MainActivity): CharactersComponent
    }
}