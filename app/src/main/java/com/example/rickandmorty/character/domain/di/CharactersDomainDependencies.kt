package com.example.rickandmorty.character.domain.di

import com.example.rickandmorty.character.domain.CharactersDomainLayerContract
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.usecase.GetAllCharactersUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class CharactersDomainDependencies {

    @Provides
    @Named("get_all_characters")
    fun providesGetAllCharactersUc(usecase: GetAllCharactersUc) : @JvmSuppressWildcards CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> = usecase

    @Provides
    @Named("get_characters_next_page")
    fun providesGetCharactersNextPageUc(usecase: GetAllCharactersUc) : @JvmSuppressWildcards CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> = usecase

}