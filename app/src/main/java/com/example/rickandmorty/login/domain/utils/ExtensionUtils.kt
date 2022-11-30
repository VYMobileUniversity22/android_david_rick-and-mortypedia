package com.example.rickandmorty.login.domain.utils

import com.example.rickandmorty.login.data.model.LoginUserDto
import com.example.rickandmorty.login.domain.model.Email
import com.example.rickandmorty.login.domain.model.Keypass
import com.example.rickandmorty.login.domain.model.LoginUser

private const val DEFAULT_STRING_VALUE = ""

fun Email.isValid(): Boolean {
    val pattern = """.+@.+\.com""".toRegex()
    return value.trim().contains(pattern)
}
private const val KEYPASS_LENGTH: Int = 5
fun Keypass.isValid(): Boolean = value.trim().length > KEYPASS_LENGTH

fun LoginUserDto.toLoginUser() =
    LoginUser(
        name = name ?: DEFAULT_STRING_VALUE,
        email = Email(value =  email ?: DEFAULT_STRING_VALUE)
    )