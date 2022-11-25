package com.example.rickandmorty.character.data.utils

import com.example.rickandmorty.character.data.db.CharacterEntity
import com.example.rickandmorty.character.data.db.LocationEntity
import com.example.rickandmorty.character.data.db.OriginEntity
import com.example.rickandmorty.character.data.model.CharacterDto
import com.example.rickandmorty.character.data.model.CharactersDto
import com.example.rickandmorty.character.data.model.LocationDto
import com.example.rickandmorty.character.data.model.OriginDto
import com.example.rickandmorty.character.domain.model.Character
import com.example.rickandmorty.character.domain.model.Characters
import com.example.rickandmorty.character.domain.model.Location
import com.example.rickandmorty.character.domain.model.Origin

fun CharactersDto?.toCharacters() : Characters =
    Characters(results = this?.results?.toCharacterList() ?: emptyList())


fun List<CharacterDto>.toCharacterList() : List<Character> =
    this.map { dto ->
        with(dto) {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = origin.toBo(),
                location = location.toBo(),
                image = image,
                episode = episode,
                url = url,
                created = created
            )
        }
    }
fun OriginDto.toBo() : Origin = Origin(name = name, url = url)
fun LocationDto.toBo() : Location = Location(name = name, url = url)


fun CharactersDto.toCharactersEntity(): List<CharacterEntity> = results.map { dto ->
    with(dto) {
        CharacterEntity(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = origin.toEntity(),
            location = location.toEntity(),
            image = image,
            episode = episode,
            url = url,
            created = created
        )
    }
}

private fun OriginDto.toEntity(): OriginEntity =
    OriginEntity(
        name = name,
        url = url
    )

private fun LocationDto.toEntity(): LocationEntity =
    LocationEntity(
        name = name,
        url = url
    )

fun List<CharacterEntity>.toCharacters(): Characters =
    Characters(
        results = this.map { entity ->
            with(entity){
                Character(
                    id = id,
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender,
                    origin = origin.toBo(),
                    location = location.toBo(),
                    image = image,
                    episode = episode,
                    url = url,
                    created = created
                )
            }

        }
    )

private fun OriginEntity.toBo(): Origin =
    Origin(
        name = name,
        url = url
    )

private fun LocationEntity.toBo():Location =
    Location(
        name = name,
        url = url
    )

