package com.example.drugstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drugstore.fragments.DashboardFragment
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.card_layout.view.item_image
import kotlinx.android.synthetic.main.card_layout.view.item_price
import kotlinx.android.synthetic.main.card_layout.view.item_title

class RecyclerAdapter(private val context: DashboardFragment): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    private var dataList = mutableListOf<Producto>()

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
        holder.boton.setOnClickListener{

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
        }
    }
}