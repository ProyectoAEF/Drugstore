package com.example.drugstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drugstore.fragments.DashboardFragment
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.card_layout.view.item_image
import kotlinx.android.synthetic.main.card_layout.view.item_price
import kotlinx.android.synthetic.main.card_layout.view.item_title

class RecyclerAdapter(private val context: DashboardFragment, private val itemClickListener: OnProductClickListener): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface OnProductClickListener{
        fun onAddProductClick(producto: Producto)
    }
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
            val boton = itemView.cart
            Glide.with(context).load(producto.imageUrl).into(itemView.item_image)
            itemView.item_title.text = producto.nombre
            itemView.item_price.text = producto.precio
            itemView.item_cantidad.text = producto.cantidad
            if(producto.prescrip == false){
                itemView.item_prescrip.text = "Producto no prescrito"
            } else{
                itemView.item_prescrip.text = "Producto prescrito"
            }
            itemView.cart.setOnClickListener{itemClickListener.onAddProductClick(producto)}
        }
    }
}