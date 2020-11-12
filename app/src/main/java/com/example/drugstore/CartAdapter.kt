package com.example.drugstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout_cart.view.*

class CartAdapter:RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private var titles = arrayOf("Hola")
    private var prices = arrayOf("$0")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_cart, parent, false)
        return this.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
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
        val boton = itemView.erase

        init{
            itemImage=itemView.findViewById(R.id.item_image)
            itemTitle=itemView.findViewById(R.id.item_title)
            itemPrice=itemView.findViewById(R.id.item_price)
        }
    }

}