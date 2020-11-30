package com.example.drugstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_pay_window.*

class PayWindow : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_window)

        ingresoDireccion.isEnabled = false
        radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            ingresoDireccion.isEnabled = checkedId == R.id.direccion1
        }
    }
}