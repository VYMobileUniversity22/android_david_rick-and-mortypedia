package com.example.rickandmorty.character.presentation.di

import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.MainPresenter
import com.example.rickandmorty.Mvp
import dagger.Module
import dagger.Provides

@Module
//Se podria usar Mvp.view en vez de MainActivity
class CharactersPresentationModule(private val mvpView: Mvp.View) {

    @Provides
    fun providesMvpView(): Mvp.View = mvpView

    @Provides
    fun providesMvpPresenter(mainPresenter: MainPresenter): Mvp.Presenter = mainPresenter

}