package com.example.rickandmorty.login.data.repository

import com.example.rickandmorty.login.data.datasource.LoginDataSource
import com.example.rickandmorty.login.domain.LoginDomainLayerContract
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser
import com.example.rickandmorty.login.domain.utils.toLoginUser


object RickAndMortyLoginRepository : LoginDomainLayerContract.DataLayer.LoginRepository {

    lateinit var loginDataSource: LoginDataSource

    override suspend fun loginUser(email: Email, keypass: Keypass): Result<LoginUser> =
        try {
            loginDataSource.signInWithEmailAndPassword(email = email, keypass = keypass)
                .map { loginUserDto -> loginUserDto.toLoginUser() }
        } catch(e: Exception) {
            Result.failure(e)
        }

}
