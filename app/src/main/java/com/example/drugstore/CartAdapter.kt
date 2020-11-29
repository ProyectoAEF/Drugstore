package com.example.drugstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.card_layout_cart.view.item_image
import kotlinx.android.synthetic.main.card_layout_cart.view.item_price
import kotlinx.android.synthetic.main.card_layout_cart.view.item_title
import java.util.ArrayList

class CartAdapter(private val context: Carrito):RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Producto>()

    fun setListData(data: MutableList<Producto>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_cart, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto: Producto = dataList[position]
        holder.bindView(producto)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0){
            return dataList.size
        }else{
            return 0
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(producto: Producto){
            Glide.with(context).load(producto.imageUrl).into(itemView.item_image)
            itemView.item_title.text = producto.nombre
            itemView.item_price.text = producto.precio
            if(producto.prescrip == "0"){
                itemView.item_prescrip.text = "Producto no prescrito"
            } else{
                itemView.item_prescrip.text = "Producto prescrito"
            }
        }
    }
}