package com.example.rickandmorty.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmorty.character.data.db.*
import com.example.rickandmorty.episode.data.db.EpisodeEntity
import com.example.rickandmorty.episode.data.db.EpisodesDao

@Database(
    //entities = [CharactersEntity::class, CharacterEntity::class, InfoEntity::class, OriginEntity::class, LocationEntity::class], version = 1
    entities = [CharacterEntity::class, EpisodeEntity::class], version = 1
)
@TypeConverters(EpisodeReferenceListConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
    abstract fun episodeDao(): EpisodesDao
}