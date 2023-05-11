package com.example.apppartysoul.controller.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppartysoul.R
import com.example.apppartysoul.adapter.TeamAdapter
import com.example.apppartysoul.controller.activities.TeamDetails
import com.example.apppartysoul.models.ApiResponseHeader
import com.example.apppartysoul.models.Team
import com.example.apppartysoul.network.TeamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TeamsFragment : Fragment(), TeamAdapter.OnItemClickListener {
    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvTeams)
        loadTeams(view.context)
    }

    private fun loadTeams(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/teams/league/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val teamService: TeamService
        teamService = retrofit.create(TeamService::class.java)

        val request = teamService.getTeams(
            host = "api-football-v1.p.rapidapi.com",
            apiKey = "8c6d3c6c92msh78e863a5d3f5175p1300fbjsna062ca1abeed"
        )

        request.enqueue(object: Callback<ApiResponseHeader> {
            override fun onResponse(
                call: Call<ApiResponseHeader>,
                response: Response<ApiResponseHeader>
            ) {
                if (response.isSuccessful) {
                    val teams = response.body()!!.api.teams ?: ArrayList()
                    recyclerView.adapter = TeamAdapter(teams, context, this@TeamsFragment)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                }
                else{
                    Log.d("TeamFragment Failed", "Error: " + response.code())
                }
            }
            override fun onFailure(call: Call<ApiResponseHeader>, t: Throwable) {
                Log.d("TeamFragment Failed", "Error: " + t.toString())
            }
        })
    }

    override fun onItemClicked(team: Team) {
        val intent = Intent(context, TeamDetails::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }
}