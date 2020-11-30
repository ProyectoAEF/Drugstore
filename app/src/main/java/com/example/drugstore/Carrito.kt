package com.example.drugstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.android.synthetic.main.activity_carrito.recyclerView
import kotlinx.android.synthetic.main.activity_carrito.settings
import kotlinx.android.synthetic.main.activity_tienda.*

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

        var botonSetting = settings
        botonSetting.setOnClickListener(){
            startActivity(Intent(this, Update_user::class.java))
        }

        var botonComprar = buy
        botonComprar.setOnClickListener(){
            startActivity(Intent(this, PayWindow::class.java))
        }
    }
}