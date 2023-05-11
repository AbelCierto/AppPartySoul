package com.example.apppartysoul.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apppartysoul.models.Team

@Database(entities = [Team::class], version = 1)
abstract class TeamDB: RoomDatabase() {
    abstract fun getTeamDao(): TeamDao

    companion object {
        private var INSTANCE: TeamDB? = null

        fun getInstance(context: Context): TeamDB {
            if (INSTANCE == null) {
                synchronized(TeamDB::class) {
                    INSTANCE = Room
                        .databaseBuilder(context, TeamDB::class.java, "team4.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as TeamDB
        }
    }
}