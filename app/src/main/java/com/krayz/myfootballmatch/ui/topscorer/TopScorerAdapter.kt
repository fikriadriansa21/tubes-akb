package com.krayz.myfootballmatch.ui.topscorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krayz.myfootballmatch.R
import com.krayz.myfootballmatch.data.network.model.topscore.TopScorer

class TopScorerAdapter() : RecyclerView.Adapter<TopScorerAdapter.ViewHolder>(){

    private var arrList : List<TopScorer?> =  ArrayList<TopScorer>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewPlayer: TextView = view.findViewById(R.id.tv_player_name)
        val textViewGoals: TextView = view.findViewById(R.id.tv_goal_score)
    }

    fun setItems(items: List<TopScorer?>) {
        arrList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_topscorer, parent, false))

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewPlayer.text = arrList[position]!!.player_name
        holder.textViewGoals.text = arrList[position]!!.goals.total.toString()
    }

}