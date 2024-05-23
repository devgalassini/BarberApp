package galassini.tecnology.barbeariaapp.view

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import galassini.tecnology.barbeariaapp.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar:Calendar = Calendar.getInstance()
    private var data:String = ""
    private var hora:String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        listeners()
    }
    private fun onTimePicked(hour:Int,minute:Int){
        val minuto:String
        if(minute < 10){
            minuto = "0$minute"
        } else{
            minuto = minute.toString()
        }

        hora = "$hour:$minuto"
        binding.timeSelected.text = hora
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun listeners(){
        val yearData = calendar.get(Calendar.YEAR)
        val mesData = calendar.get(Calendar.MONTH)
        val diaData = calendar.get(Calendar.DAY_OF_MONTH)
        val nome = intent.extras?.getString("nome").toString()

        binding.datePicker.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                var dia = day.toString()
                val mes: String
                if(day < 10 ){
                    dia = "0$day"
                }
                if(month < 10){
                    mes = "" + (month+1)
                }else{
                    mes = (month+1).toString()
                }
                data = "$dia / $mes / $year"
                binding.dateSelected.text = data
            }, yearData, mesData, diaData).show()
        }
        binding.defaultTimePicker.setOnClickListener {
            SnapTimePickerDialog.Builder().apply {
                setTitle("Selecione o horario")
                setTitleColor(android.R.color.white)

            }.build().apply {
                setListener{
                        hour,minute -> onTimePicked(hour,minute)
                }
            }.show(supportFragmentManager,SnapTimePickerDialog.TAG)
        }
        binding.btAgendar.setOnClickListener {
            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            when{
                hora.isEmpty()->{
                    mensagem(it,"Preencha o horário!","#FF0000")
                }
                hora < "8:00" && hora > "19:00" ->{
                    mensagem(it,"Barber Shop esta fechado!","#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it,"Preencha a data!","#FF0000")
                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it,nome,"Pedro da Silva",data,hora)
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it,nome,"Marcos Duarte Gomes",data,hora)
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it,nome,"Cleber Gomes",data,hora)
                }
                else -> {
                    mensagem(it,"Escolha um Barbeiro","#FF0000")
                }
            }

        }
    }

    private fun mensagem(view: View, mensagem:String,cor:String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
    private fun salvarAgendamento(view:View, cliente: String, barbeiro:String,data:String,hora:String){
        val db = FirebaseFirestore.getInstance()
        val dadosUsuario = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora,
        )

        db.collection("agendamento").document(cliente).set(dadosUsuario).addOnSuccessListener {
            mensagem(view,"Agendamento realizado com sucesso","#FF03DAC5")
        }.addOnFailureListener {
            mensagem(view,"Erro no servidor!","#FF0000")
        }
    }
}