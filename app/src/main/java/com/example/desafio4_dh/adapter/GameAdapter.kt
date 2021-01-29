package com.example.desafio4_dh.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio4_dh.model.Game

class GameAdapter (private val listaGames: List<Game>): RecyclerView.Adapter<GameAdapter.GameViewHolder>(){

    class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}