package com.example.drugstore.carrito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drugstore.Producto
import com.example.drugstore.R
import com.example.drugstore.gestion_usuario.Update_user
import com.example.drugstore.pago.bdpres.PayWin
import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.android.synthetic.main.activity_carrito.recyclerView
import kotlinx.android.synthetic.main.activity_carrito.settings

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
            startActivity(Intent(this, PayWin::class.java).apply {
                putExtra("nombre",producto.nombre)
                putExtra("precio",producto.precio)
                putExtra("prescri",producto.prescrip)
            })
        }
    }
}