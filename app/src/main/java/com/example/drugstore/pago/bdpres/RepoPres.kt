package com.example.drugstore.pago.bdpres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.drugstore.Prescripcion
import com.google.firebase.firestore.FirebaseFirestore

class RepoPres {

    fun getProductoData():LiveData<MutableList<Prescripcion>>{
        val mutableData = MutableLiveData<MutableList<Prescripcion>>()
        FirebaseFirestore.getInstance().collection("Prescripcion").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Prescripcion>()
            for(document in result){
                val nombre = document.getString("nombre")
                val codigo = document.getString("codigo")
                var prescripcion = Prescripcion(nombre!!,codigo!!)
                listData.add(prescripcion)
            }
            mutableData.value = listData
        }
        return mutableData
    }

}