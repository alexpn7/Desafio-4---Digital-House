package com.example.desafio4_dh.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.desafio4_dh.R
import com.example.desafio4_dh.adapter.GameAdapter
import com.example.desafio4_dh.model.Game
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), GameAdapter.OnClickGameListener {

    val gameViewModel : MainViewModel by viewModels()
    val listaGames = arrayListOf<Game>()
    var adapter = GameAdapter(listaGames, this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGames.adapter = adapter
        rvGames.layoutManager = GridLayoutManager(this, 2)

        fabAdd.setOnClickListener {
            startActivity(Intent(this, GameCadastroActivity::class.java))
        }

        gameViewModel.listaDeGames.observe(this, Observer {
            adapter.addCollection(it)
        })

        gameViewModel.readListaGames()
    }

    override fun onClickGame(position: Int) {
        val myGame = gameViewModel.listaDeGames.value?.get(position)
        val intent = Intent(this, GamesDetailActivity::class.java)
        if (myGame != null) {
            intent.putExtra("nome", myGame.nome)
            intent.putExtra("ano", myGame.ano)
            intent.putExtra("descricao", myGame.descricao)
            intent.putExtra("url", myGame.url)
        }
        startActivity(intent)

    }
}