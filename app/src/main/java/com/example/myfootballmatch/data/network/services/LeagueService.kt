package com.example.myfootballmatch.data.network.services

import com.example.myfootballmatch.data.network.model.league.ApiLeague
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LeagueService {
    @GET("/seasons/{season}")
    fun getListDataLeague(@Path("season") season: Int): Call<ApiLeague>
}