package com.example.rickandmorty.character.data.utils

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
