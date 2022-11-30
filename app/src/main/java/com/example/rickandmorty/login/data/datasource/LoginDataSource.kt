package com.example.rickandmorty.login.data.datasource

import com.example.rickandmorty.login.data.model.LoginUserDto
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface LoginDataSource {
    suspend fun signInWithEmailAndPassword(email: Email, keypass: Keypass): Result<LoginUserDto>
}

class FirebaseAuthDataSource @Inject constructor(
    private val fbAuthInstance: FirebaseAuth
) : LoginDataSource {


    override suspend fun signInWithEmailAndPassword(email: Email, keypass: Keypass): Result<LoginUserDto> =
        fbAuthInstance.signInWithEmailAndPassword(email.value, keypass.value).await()
            .runCatching { toLoginUserDto() }
    }



fun AuthResult.toLoginUserDto(): LoginUserDto =
    LoginUserDto(
        name = user?.displayName,
        email = user?.email
    )