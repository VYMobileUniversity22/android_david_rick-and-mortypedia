package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.CharactersComponent
import com.example.rickandmorty.di.DaggerCharactersComponent

class RickAndMortyApplication: Application() {

    val appComponent: CharactersComponent by lazy {
        DaggerCharactersComponent.factory().create(MainActivity())
    }

}