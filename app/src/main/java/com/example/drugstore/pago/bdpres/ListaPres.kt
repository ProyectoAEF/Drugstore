package com.example.drugstore.pago.bdpres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drugstore.Prescripcion


class ListaPres:ViewModel() {

    private val repoPres = RepoPres()

    fun fetchProductoData(): LiveData<MutableList<Prescripcion>> {
        val mutableData = MutableLiveData<MutableList<Prescripcion>>()
        repoPres.getProductoData().observeForever{productoList ->
            mutableData.value = productoList
        }
        return mutableData
    }
}