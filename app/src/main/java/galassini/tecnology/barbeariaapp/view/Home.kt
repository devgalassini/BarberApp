package galassini.tecnology.barbeariaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import galassini.tecnology.barbeariaapp.R
import galassini.tecnology.barbeariaapp.adapter.ServicosAdapter
import galassini.tecnology.barbeariaapp.database.AppSharedPreferences
import galassini.tecnology.barbeariaapp.databinding.ActivityHomeBinding
import galassini.tecnology.barbeariaapp.model.Servicos


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos:MutableList<Servicos> = mutableListOf()
    private lateinit var sharedPreferences: AppSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = AppSharedPreferences(applicationContext)
        supportActionBar?.hide()
        val nome = sharedPreferences.get("isLogged")
        binding.txtNomeUsuario.text = "Bem-vindo, $nome"

        configRecycler()
        listeners(nome!!)
    }

    private fun configRecycler(){
        val recyclerServicos = binding.recyclerViewServicos
        recyclerServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        recyclerServicos.setHasFixedSize(true)
        recyclerServicos.adapter = servicosAdapter
        getServicos()
    }

    private fun listeners(nome:String){
        binding.btAgendar.setOnClickListener {
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }
    }

    private fun getServicos(){
        val servico1 = Servicos(R.drawable.tesoura,"Corete de Cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.bigode,"Corete de barba")
        listaServicos.add(servico2)

        val servico3= Servicos(R.drawable.lavarcabelo,"Lavagem de cabelo")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.gel,"Tratamento de cabelo")
        listaServicos.add(servico4)
    }
}