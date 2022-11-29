package com.example.rickandmorty.common.utils

import com.example.rickandmorty.character.data.db.LocationEntity
import com.example.rickandmorty.character.data.db.OriginEntity
import com.example.rickandmorty.character.data.model.LocationDto
import com.example.rickandmorty.character.data.model.OriginDto
import com.example.rickandmorty.character.domain.model.Location
import com.example.rickandmorty.character.domain.model.Origin


fun OriginDto.toBo() : Origin = Origin(name = name, url = url)

fun LocationDto.toBo() : Location = Location(name = name, url = url)

fun OriginDto.toEntity(): OriginEntity =
    OriginEntity(
        name = name,
        url = url
    )

fun LocationDto.toEntity(): LocationEntity =
    LocationEntity(
        name = name,
        url = url
    )

fun OriginEntity.toBo(): Origin =
    Origin(
        name = name,
        url = url
    )

fun LocationEntity.toBo(): Location =
    Location(
        name = name,
        url = url
    )
