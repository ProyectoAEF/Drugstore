package com.example.drugstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var titles = arrayOf("Loratadina","Dolex","Ibuprofeno","Cloranferamina","Losartan","Paracetamol","Aspirina","Omeprazol","Atorvastatina")
    private var prices = arrayOf("$0","$0","$0","$0","$0","$0","$0","$0","$0")
    //private val images = intArrayOf(R.drawable.nameImage)
    //Se deben agregar a la carpeta drawable las imagenes de los medicamentos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemPrice.text = prices[position]
        //holder.itemImage = setImageResource(images[position])
        holder.boton.setOnClickListener{
            
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView
        val boton = itemView.cart

        init{
            itemImage=itemView.findViewById(R.id.item_image)
            itemTitle=itemView.findViewById(R.id.item_title)
            itemPrice=itemView.findViewById(R.id.item_price)
        }
    }
}