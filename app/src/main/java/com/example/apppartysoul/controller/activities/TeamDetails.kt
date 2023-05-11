package com.example.apppartysoul.controller.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.apppartysoul.R
import com.example.apppartysoul.database.TeamDB
import com.example.apppartysoul.models.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class TeamDetails : AppCompatActivity() {

    lateinit var ivLogoDetail: ImageView
    lateinit var tvNameDetail: TextView
    lateinit var tvVenueName: TextView
    lateinit var fabSave: FloatingActionButton
    lateinit var fabRaise: FloatingActionButton
    lateinit var fabDecrease: FloatingActionButton
    lateinit var tvRating: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        ivLogoDetail = findViewById(R.id.ivLogoDetail)
        tvNameDetail = findViewById(R.id.tvNameDetail)
        tvVenueName = findViewById(R.id.tvVenueDetail)
        tvRating = findViewById(R.id.tvRating)
        fabSave = findViewById(R.id.fabSave)
        fabRaise = findViewById(R.id.fabRaise)
        fabDecrease = findViewById(R.id.fabDecrease)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val teamObject: Team? = intent.getSerializableExtra("team") as Team?

        val picBuilder = Picasso.Builder(context)
        picBuilder.build().load(teamObject?.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(ivLogoDetail)

        tvNameDetail.text = teamObject?.name
        tvVenueName.text = teamObject?.venue_name
        tvRating.text = teamObject?.rating.toString()

        fabDecrease.setOnClickListener{
            var rating = tvRating.text.toString().toInt()
            if (rating > 0) {
                rating--
                tvRating.text = rating.toString()
            }
        }

        fabRaise.setOnClickListener{
            var rating = tvRating.text.toString().toInt()
            if (rating < 5) {
                rating++
                tvRating.text = rating.toString()
            }
        }

        fabSave.setOnClickListener{
            teamObject?.rating = tvRating.text.toString().toInt()
            saveTeam(teamObject)
            finish()
        }

    }

    private fun saveTeam(teamObject: Team?) {
        if (teamObject != null) {
            TeamDB.getInstance(this).getTeamDao().insertTeam(teamObject)
        }
    }
}