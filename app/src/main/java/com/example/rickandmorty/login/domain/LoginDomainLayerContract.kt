package com.example.rickandmorty.login.domain

import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser

interface LoginDomainLayerContract {
    interface PresentationLayer {
        interface UseCase<T> {

            //suspend fun getAllCharacters(): Result<Characters>
            //block: () -> Result<T>
            suspend operator fun invoke(email: Email, keypass: Keypass): Result<T>

        }
    }

    interface DataLayer {

        interface LoginRepository {

          suspend fun loginUser(email: Email, keypass: Keypass) : Result<LoginUser>
        }
    }
}