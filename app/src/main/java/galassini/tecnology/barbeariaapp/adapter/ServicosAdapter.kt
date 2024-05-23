package galassini.tecnology.barbeariaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import galassini.tecnology.barbeariaapp.databinding.ServicosItemBinding
import galassini.tecnology.barbeariaapp.model.Servicos


class ServicosAdapter(private val context: Context,
                      private val listaServicos: MutableList<Servicos>
) : RecyclerView.Adapter<ServicosAdapter.ServicosViewHolder>() {
    inner class ServicosViewHolder(binding: ServicosItemBinding) : RecyclerView.ViewHolder(binding.root){
        val imgServico = binding.imgServicos
        val txtServico = binding.txtServico
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ServicosViewHolder(itemLista)
    }

    override fun getItemCount() = listaServicos.size

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        holder.imgServico.setImageResource(listaServicos[position].img!!)
        holder.txtServico.text = listaServicos[position].nome
    }


}