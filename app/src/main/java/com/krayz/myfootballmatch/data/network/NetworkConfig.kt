package com.krayz.myfootballmatch.data.network

import com.krayz.myfootballmatch.data.network.services.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    private val logger = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val headerInterceptor = object: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val original : Request = chain.request()

            val request = original.newBuilder()
                .header("x-rapidapi-host","api-football-v1.p.rapidapi.com")
                .header("x-rapidapi-key","0f8e9f6a8emsh3f7b707df800091p167597jsn6c4c09a752d2")
//                .header("x-rapidapi-key","da2296771bmsh53b2c02cf954dd9p11db83jsnc23498be70d0")
                .header("useQueryString","true")
                .method(original.method,original.body)
                .build()

            return chain.proceed(request)
        }
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)

    private val builder = Retrofit.Builder()
        .baseUrl("https://api-football-v1.p.rapidapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()

    val leagueService: LeagueService = builder.create(LeagueService::class.java)
    val teamService: TeamService = builder.create(TeamService::class.java)
    val searchTeamService: SearchTeamService = builder.create(SearchTeamService::class.java)
    val fixtureTeamService: FixtureTeamService = builder.create(FixtureTeamService::class.java)
    val fixtureLeagueService: FixtureLeagueService = builder.create(FixtureLeagueService::class.java)
    val standingService: StandingService = builder.create(StandingService::class.java)
    val playerService: PlayerService = builder.create(PlayerService::class.java)
    val topScoreService: TopScoreService = builder.create(TopScoreService::class.java)
    val statisticService: StatisticService = builder.create(StatisticService::class.java)


}