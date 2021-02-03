package com.example.desafio4_dh.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio4_dh.R
import com.example.desafio4_dh.model.Game
import com.example.desafio4_dh.ui.GamesDetailActivity
import com.squareup.picasso.Picasso

//class GameAdapter (val context: Context): RecyclerView.Adapter<GameAdapter.GameViewHolder>(){
class GameAdapter (var listaGames: ArrayList<Game>, val listener: OnClickGameListener): RecyclerView.Adapter<GameAdapter.GameViewHolder>(){

    //val listaGames = arrayListOf<Game>()

    override fun onCreateViewHolder(group: ViewGroup, type: Int): GameViewHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.item_game, group, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = listaGames[position]
        /*Log.i("URL", game.url + " " + game.url.toString())
        Log.i("Nome", game.nome)
        Log.i("Ano", game.ano)
        if (game.url != null)*/
        Picasso.get().load(game.url).into(holder.ivGameImg)
        holder.tvGameNome.text = game.nome
        holder.tvGameAno.text = game.ano

        /*
        holder.ivGameImg.setOnClickListener {
            (context as OnClickGame).onClickGame(position)
            context.startActivity(Intent(context, GamesDetailActivity::class.java)
                    .putExtra("game", game.url))
        }
         */
    }

    override fun getItemCount() = listaGames.size

    interface OnClickGameListener {
        fun onClickGame(position: Int)
    }

    fun addCollection(gameCollection: ArrayList<Game>) {
        listaGames = gameCollection
        notifyDataSetChanged()
    }

    inner class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ivGameImg: ImageView = itemView.findViewById(R.id.ivGameImg)
        var tvGameNome: TextView = itemView.findViewById(R.id.tvGameNome)
        var tvGameAno: TextView = itemView.findViewById(R.id.tvGameAno)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION)
                listener.onClickGame(position)
        }
    }
}