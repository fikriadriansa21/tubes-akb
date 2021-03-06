package com.krayz.myfootballmatch.ui.fixture.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krayz.myfootballmatch.R
import com.krayz.myfootballmatch.data.network.model.fixture.Fixture

class FixtureAdapter(private var listener: FixtureListener) : RecyclerView.Adapter<FixtureAdapter.ViewHolder>(){

    private var arrList : List<Fixture?> =  ArrayList<Fixture>()
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageViewClubHome: ImageView = view.findViewById(R.id.iv_club_logo_fav_team)
        val imageViewClubAway: ImageView = view.findViewById(R.id.iv_club_logo_opponent)
        val textViewClubHome: TextView = view.findViewById(R.id.tv_team_nick_name)
        val textViewClubAway: TextView = view.findViewById(R.id.tv_team_nick_name_opponent)
        val textViewScoreHome: TextView = view.findViewById(R.id.tv_score_club)
        val textViewScoreAway: TextView = view.findViewById(R.id.tv_score_opponent)
    }

    fun setItems(items: List<Fixture?>) {
        arrList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_matchweek, parent, false)
        )

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(arrList[position]!!.homeTeam.logo)
            .into(holder.imageViewClubHome)
        Glide.with(holder.itemView.context)
            .load(arrList[position]!!.awayTeam.logo)
            .into(holder.imageViewClubAway)

        holder.textViewClubHome.text = arrList[position]!!.homeTeam.team_name
        holder.textViewClubAway.text = arrList[position]!!.awayTeam.team_name
        holder.textViewScoreHome.text = arrList[position]!!.goalsHomeTeam.toString()
        holder.textViewScoreAway.text = arrList[position]!!.goalsAwayTeam.toString()
        holder.itemView.setOnClickListener {
            arrList[position]?.fixture_id?.let { it1 -> listener.onFixtureListener(it1) }
        }
    }

    interface FixtureListener {
        fun onFixtureListener(id: Int)
    }
}