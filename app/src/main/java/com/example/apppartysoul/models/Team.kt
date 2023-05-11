package com.example.apppartysoul.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "teams"
)
class Team (
    @PrimaryKey
    @SerializedName("team_id")
    val team_id : Int = 0,
    @SerializedName("name")
    val name : String,
    @SerializedName("logo")
    var logo: String,
    @SerializedName("venue_name")
    var venue_name: String,
    @SerializedName("rating")
    var rating: Int = 0,

    ): Serializable
