package com.example.rickandmorty.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.character.data.db.*

@Database(
    //entities = [CharactersEntity::class, CharacterEntity::class, InfoEntity::class, OriginEntity::class, LocationEntity::class], version = 1
    entities = [TestEntity::class], version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    //abstract fun characterDao(): CharactersDao
    abstract fun testDao(): TestDao
}