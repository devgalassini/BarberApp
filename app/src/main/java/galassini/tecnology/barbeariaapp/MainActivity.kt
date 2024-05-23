package galassini.tecnology.barbeariaapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import galassini.tecnology.barbeariaapp.database.AppSharedPreferences
import galassini.tecnology.barbeariaapp.databinding.ActivityMainBinding
import galassini.tecnology.barbeariaapp.view.Home

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: AppSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = AppSharedPreferences(application.applicationContext)
        supportActionBar?.hide()
        verifyLogged()
        listeners()

    }

    private fun listeners(){
        binding.btnLogin.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                nome.isEmpty() -> {
                    mensagem(it,"Coloque o seu nome!")
                }
                senha.isEmpty() -> {
                    mensagem(it,"Coloque sua senha!")
                }
                senha.length <= 5 -> {
                    mensagem(it,"A senha precisa ter pelo menos 6 caracteres!")
                }else -> {
                navegarPraHome(nome)
            }
            }
        }
    }


    private fun verifyLogged(){
        if(sharedPreferences.get("isLogged") != ""){
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }


    private fun mensagem(view: View, mensagem:String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor(("#FFFFFF")))
        snackbar.show()
    }

    private fun navegarPraHome(nome:String){
        sharedPreferences.store("isLogged",nome)
        val intent = Intent(this,Home::class.java)
        intent.putExtra("nome",nome)
        startActivity(intent)
    }
}