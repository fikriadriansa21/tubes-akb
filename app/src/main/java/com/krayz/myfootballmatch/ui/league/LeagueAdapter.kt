package com.krayz.myfootballmatch.ui.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krayz.myfootballmatch.R
import com.krayz.myfootballmatch.data.network.model.league.League

class LeagueAdapter(private var listener: LeagueListener) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>(){

    private var arrList : List<League?> =  ArrayList<League>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageViewClub: ImageView = view.findViewById(R.id.iv_league_logo)
        val textViewClub: TextView = view.findViewById(R.id.tv_league_name)
    }

    fun setItems(items: List<League?>) {
        arrList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_league, parent, false))

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(arrList[position]!!.logo)
            .into(holder.imageViewClub)

        holder.textViewClub.text = arrList?.get(position)?.name
        holder.itemView.setOnClickListener {
            listener.onLeagueListener(arrList[position]!!.league_id!!, arrList[position]!!.name!!, arrList[position]!!.logo!!)
        }
    }

    interface LeagueListener {
        fun onLeagueListener(id: Int, name: String, logo: String)
    }
}