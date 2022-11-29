package com.example.rickandmorty.episode.data.db

import androidx.room.*

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM episode_table")
    suspend fun getAllEpisodes(): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg episodes: EpisodeEntity)

    @Delete
    suspend fun delete(episodes: EpisodeEntity)

}