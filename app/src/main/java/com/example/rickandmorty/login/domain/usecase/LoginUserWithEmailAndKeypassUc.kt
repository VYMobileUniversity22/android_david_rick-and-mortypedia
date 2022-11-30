package com.example.rickandmorty.login.domain.usecase

import android.nfc.FormatException
import com.example.rickandmorty.login.domain.LoginDomainLayerContract
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser
import com.example.rickandmorty.login.domain.utils.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginUserWithEmailAndKeypassUc @Inject constructor(
    private val loginRepository: LoginDomainLayerContract.DataLayer.LoginRepository
) : LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser> {

    override suspend fun invoke(email: Email, keypass: Keypass): Result<LoginUser> =
        withContext(Dispatchers.IO) {
            if (email.isValid() && keypass.isValid()) {
                loginRepository.loginUser(email = email, keypass = keypass)
            } else {
                Result.failure(FormatException("E-mail or/and keypass are incorrect"))
            }
        }

}