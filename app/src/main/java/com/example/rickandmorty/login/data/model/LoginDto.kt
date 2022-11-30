package com.example.rickandmorty.login.data.model

import com.example.rickandmorty.login.domain.model.Email

data class LoginUserDto(
    val name: String?,
    val email: String?
)