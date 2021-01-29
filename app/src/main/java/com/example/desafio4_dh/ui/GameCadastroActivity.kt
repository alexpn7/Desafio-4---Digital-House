package com.example.desafio4_dh.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.desafio4_dh.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_games_cadastro.*

class GameCadastroActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_cadastro)
        config()

        fbUpload.setOnClickListener {
            setIntent()
        }
    }

    fun config() {
        //FirebaseApp.initializeApp(Context)
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        //storageReference = FirebaseStorage.getInstance().getReference("prod_img")
    }

    //Configura a intent para obter a imagem da galeria
    fun setIntent() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Imagem a ser capturada"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG) {
            alertDialog.show()
            //passa o nome do arquivo p/ a referência do firebase storage
            storageReference = FirebaseStorage.getInstance().getReference(data!!.dataString!!)
            val uploadTask = storageReference.putFile(data!!.data!!)
            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Imagem Armazenada no Firebase", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    Log.i("URI", downloadUri.toString())
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("URL sem o token", url)
                    alertDialog.dismiss()
                    Picasso.get().load(url).into(ivResult)
                }

            }
        }
    }
}