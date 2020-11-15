package com.example.drugstore.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drugstore.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), RecyclerAdapter.OnProductClickListener {

    private lateinit var adapter: RecyclerAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        adapter = RecyclerAdapter(this, this)
        recyclerView.adapter = adapter

        observeData()
    }

    fun observeData() {
        viewModel.fetchProductoData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onAddProductClick(producto: Producto){
        val intent = Intent(context, ShoppingCartFragment::class.java)
        intent.putExtra("IdProducto", producto.idProducto)
        intent.putExtra("imageUrl", producto.imageUrl)
        intent.putExtra("NombreProducto", producto.nombre)
        intent.putExtra("PrecioProducto", producto.precio)
        startActivity(intent)
    }
}