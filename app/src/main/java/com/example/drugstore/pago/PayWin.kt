package com.example.drugstore.pago.bdpres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.drugstore.Prescripcion
import com.example.drugstore.Producto
import com.example.drugstore.R
import kotlinx.android.synthetic.main.activity_pay_win.*

class PayWin : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(ListaPres::class.java) }
    private lateinit var pres: EditText
    var prescritos = mutableListOf<Prescripcion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_win)

        observeData()
        var propre = Producto("D","D","D","D","D","D")
        propre.nombre = intent.getStringExtra("nombre").toString()
        propre.precio = intent.getStringExtra("precio").toString()
        propre.prescrip = intent.getStringExtra("prescri").toString()
        pres=findViewById(R.id.ingresoPrescrip)


        if (propre.prescrip == "1"){
            textPrescrip.visibility = View.VISIBLE
            ingresoPrescrip.visibility = View.VISIBLE
            line1.visibility = View.VISIBLE
            confirmar.visibility = View.VISIBLE
        }else{
            textDireccion.visibility = View.VISIBLE
            radioGroup1.visibility = View.VISIBLE
            line2.visibility = View.VISIBLE
            textPago.visibility = View.VISIBLE
            radioGroup2.visibility = View.VISIBLE
            continuar.visibility = View.VISIBLE
        }

        radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            ingresoDireccion.isEnabled = checkedId == R.id.direccion1
        }

    }

    fun comprobar(view: View){
        val prescri:String = pres.text.toString()
        for (producto in prescritos){
            if (prescri == producto.codigo){
                Toast.makeText(this, "Codigo Correcto", Toast.LENGTH_SHORT).show()
                ingresoPrescrip.isEnabled = false
                textDireccion.visibility = View.VISIBLE
                radioGroup1.visibility = View.VISIBLE
                line2.visibility = View.VISIBLE
                textPago.visibility = View.VISIBLE
                radioGroup2.visibility = View.VISIBLE
                continuar.visibility = View.VISIBLE
            }else{
                Toast.makeText(this, "Codigo Incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun observeData() {
        viewModel.fetchProductoData().observe(this, Observer {
            prescritos = it
        })
    }
}