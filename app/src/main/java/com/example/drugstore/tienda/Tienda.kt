package com.example.drugstore.tienda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drugstore.carrito.Carrito
import com.example.drugstore.tienda.bdproductos.MainViewModel
import com.example.drugstore.R
import com.example.drugstore.gestion_usuario.Update_user
import kotlinx.android.synthetic.main.activity_tienda.*


class Tienda : AppCompatActivity(){

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        adapter = RecyclerAdapter(this)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        observeData()

        var botonSetting = settings
        botonSetting.setOnClickListener(){
            startActivity(Intent(this, Update_user::class.java))
        }

        var botonCarrito = carrito
        botonCarrito.setOnClickListener(){
            startActivity(Intent(this, Carrito::class.java))
        }
    }

    fun observeData() {
        viewModel.fetchProductoData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}
