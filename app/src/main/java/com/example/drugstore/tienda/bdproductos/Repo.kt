package com.example.drugstore.tienda.bdproductos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.drugstore.Producto
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    fun getProductoData():LiveData<MutableList<Producto>>{
        val mutableData = MutableLiveData<MutableList<Producto>>()
        FirebaseFirestore.getInstance().collection("Productos").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Producto>()
            for(document in result){
                val idProducto = document.toString()
                val imageUrl = document.getString("imageUrl")
                val nombre = document.getString("nombre")
                val precio = document.getString("precio")
                val cantidad = document.getString("cantidad")
                val prescrip = document.getString("prescrip")
                var producto = Producto(idProducto!!,imageUrl!!,nombre!!,precio!!,cantidad!!,prescrip!!)

                listData.add(producto)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}