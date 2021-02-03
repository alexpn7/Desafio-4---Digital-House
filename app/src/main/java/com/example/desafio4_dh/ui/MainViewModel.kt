package com.example.desafio4_dh.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio4_dh.model.Game
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.Document
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val listaDeGames = MutableLiveData<ArrayList<Game>>()
    val urlImgSelected = MutableLiveData<Int>()
    private val listaGames = arrayListOf<Game>()
    private val firestore = FirebaseFirestore.getInstance()
    private val cr = firestore.collection("games")

    fun readListaGames(){
        viewModelScope.launch {
            cr.get().addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        val game = Game(document.data["nome"] as String,
                                document.data["ano"] as String,
                                document.data["descricao"] as String,
                                document.data["url"] as String)
                        listaGames.add(game)
                    }
                    listaDeGames.value.let {
                        listaDeGames.value = listaGames
                    }
                } else {
                    Log.w("Erro!", "Erro ao recuperar lista de jogos", it.exception)
                }
            }
        }
    }

    fun sendGame(game: Game) {
        viewModelScope.launch {
            val docuToSend = prepareFireBaseDocument(game)
            cr.document(game.nome).set(docuToSend).addOnSuccessListener {
                Log.i("Success ", "Adicionado ao firebase com sucesso !!!")
                //readListaGames()
            }.addOnFailureListener {
                Log.i("Erro ao criar game: ", it.toString())
            }
        }
    }

    /*fun getImagePosition(position: Int) {
        urlImgSelected.value = position
    }*/

    fun prepareFireBaseDocument(game: Game): MutableMap<String, Any> {
        var doc: MutableMap<String, Any> = HashMap()
        doc["nome"] = game.nome
        doc["ano"]  = game.ano
        doc["descricao"] = game.descricao
        doc["url"] = game.url
        return doc
    }
}