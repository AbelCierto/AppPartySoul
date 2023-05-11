package com.example.apppartysoul.controller.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.apppartysoul.R
import com.example.apppartysoul.database.TeamDB
import com.example.apppartysoul.models.ApiResponseHeader
import com.example.apppartysoul.models.Team
import com.example.apppartysoul.network.TeamService
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BestTeamFragment : Fragment() {

    lateinit var tvBestTeamName: TextView
    lateinit var ivLogo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBestTeamName = view.findViewById(R.id.tvBestTeamName)
        ivLogo = view.findViewById(R.id.ivLogo)
        //loadTeams(view.context)
        loadBestTeam(view.context)
    }

    private fun loadBestTeam(context: Context) {
        val teamObject: Team? = TeamDB.getInstance(context)?.getTeamDao()?.getBestTeam()
        if (teamObject != null && teamObject.rating != 0) {
            tvBestTeamName.text = teamObject.name

            val picasso = Picasso.Builder(context)
                .downloader(OkHttp3Downloader(context))
                .build()

            picasso.load(teamObject.logo)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivLogo)
        } else {
            tvBestTeamName.text = "No team found yet"
            val picasso = Picasso.Builder(context)
                .downloader(OkHttp3Downloader(context))
                .build()

            picasso.load("https://cdn.pixabay.com/photo/2017/02/12/21/29/false-2061132__340.png")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivLogo)
        }
    }
}