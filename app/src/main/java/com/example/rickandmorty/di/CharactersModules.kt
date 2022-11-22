package com.example.rickandmorty.di

import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.Mvp
import dagger.Module
import dagger.Provides

@Module
object CharactersModules {
    @Provides
    fun providesMvpView(mainActivity: MainActivity): Mvp.View = mainActivity
}