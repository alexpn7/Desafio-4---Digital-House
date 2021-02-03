package com.example.desafio4_dh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.desafio4_dh.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_detail.*

class GamesDetailActivity : AppCompatActivity() {

    val gameViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)

        Log.i("****nome****", intent.getStringExtra("nome").toString())
        Picasso.get().load(intent.getStringExtra("url")).into(iv_detailGame)
        tv_title_game_capa.text = intent.getStringExtra("nome")
        tv_titleDetail.text = intent.getStringExtra("nome")
        tv_lancamento.text = intent.getStringExtra("ano")
        tv_descricaoGame.text = intent.getStringExtra("descricao")

         /*
        gameViewModel.urlImgSelected.observe(this, {
            Picasso.get().load(intent.getStringExtra("nomes")).into(iv_detailGame)
            tv_title_game_capa.text = gameViewModel.listaDeGames.value!![it].nome
            tv_titleDetail.text = gameViewModel.listaDeGames.value!![it].nome
            tv_lancamento.text = gameViewModel.listaDeGames.value!![it].ano
            tv_descricaoGame.text = gameViewModel.listaDeGames.value!![it].descricao
        })*/
    }
}