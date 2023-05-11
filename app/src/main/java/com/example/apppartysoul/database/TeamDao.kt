package com.example.apppartysoul.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.apppartysoul.models.Team

@Dao
interface TeamDao {
    @Insert
    fun insertTeam(vararg team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): List<Team>

    @Delete
    fun deleteTeam(vararg team: Team)

    @Update
    fun updateTeam(vararg team: Team)

    @Query("SELECT * FROM teams ORDER BY rating DESC LIMIT 1")
    fun getBestTeam(): Team?

}