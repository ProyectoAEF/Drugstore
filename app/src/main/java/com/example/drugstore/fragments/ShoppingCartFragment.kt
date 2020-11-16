package com.example.drugstore.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drugstore.*
import kotlinx.android.synthetic.main.card_layout_cart.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCartFragment : Fragment() {

    private lateinit var adapter: CartAdapter
    var carritoNombre:String = ""
    var carritoPrecio:String = ""


    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        adapter = CartAdapter(this)
        recyclerView.adapter = adapter

        carritoNombre = arguments?.getString("NombreProduc").toString()
        carritoPrecio = arguments?.getString("PrecioProduc").toString()

        var producto = Producto ("Default","Default",carritoNombre,carritoPrecio,"default",false)

        val listadata = mutableListOf<Producto>()
        listadata.add(producto)

        adapter.setListData(listadata)
        adapter.notifyDataSetChanged()

        buy.setOnClickListener() {
            val intent = Intent(context, PayWindow::class.java)
            startActivity(intent)
        }

    }
}