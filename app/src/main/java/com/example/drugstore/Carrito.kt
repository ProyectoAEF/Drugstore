package com.example.drugstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.ArrayList


class Carrito : AppCompatActivity() {

    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)


        val listadata = mutableListOf<Producto>()
        var producto = Producto("0","0","0","0","0","0")
        producto.nombre = intent.getStringExtra("nombre").toString()
        producto.precio = intent.getStringExtra("precio").toString()
        producto.imageUrl = intent.getStringExtra("imagen").toString()
        producto.prescrip = intent.getStringExtra("prescri").toString()

        listadata.add(producto)

        adapter = CartAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.setListData(listadata)
        adapter.notifyDataSetChanged()

    }
}