package com.example.rickandmorty.character.presentation.di

import com.example.rickandmorty.MainPresenter
import com.example.rickandmorty.Mvp
import dagger.Module
import dagger.Provides

@Module
//Se podria usar Mvp.view en vez de MainActivity
class CharactersPresentationModule(private val mainView: Mvp.View) {

    @Provides
    fun providesMvpView(): Mvp.View = mainView

    @Provides
    fun providesMvpPresenter(mainPresenter: MainPresenter): Mvp.Presenter = mainPresenter

}