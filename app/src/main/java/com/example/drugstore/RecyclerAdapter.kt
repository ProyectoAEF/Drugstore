package com.example.drugstore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tienda_p.view.*
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.card_layout.view.item_image
import kotlinx.android.synthetic.main.card_layout.view.item_price
import kotlinx.android.synthetic.main.card_layout.view.item_title
import java.io.Serializable

class RecyclerAdapter(private val context: TiendaP): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface CommunicatorOne{
        fun passDataOne(nombreProduc:String, priceProduc: String )
    }
    private var dataList = mutableListOf<Producto>()
    private var carritolist = arrayListOf<Producto>()
    fun setListData(data:MutableList<Producto>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val producto: Producto = dataList[position]
        holder.bindView(producto)
        holder.boton.setOnClickListener {
            val intent = Intent(context, Carrito::class.java).apply {
                putExtra("nombre",producto.nombre)
                putExtra("precio",producto.precio)
                putExtra("imagen",producto.imageUrl)
                putExtra("prescri",producto.prescrip)
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else{
            return 0
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val boton = itemView.cart
        fun bindView(producto: Producto){

            Glide.with(context).load(producto.imageUrl).into(itemView.item_image)
            itemView.item_title.text = producto.nombre
            itemView.item_price.text = producto.precio
            itemView.item_cantidad.text = producto.cantidad
            if(producto.prescrip == "0"){
                itemView.item_prescrip.text = "Producto no prescrito"
            } else{
                itemView.item_prescrip.text = "Producto prescrito"
            }
        }
    }
}