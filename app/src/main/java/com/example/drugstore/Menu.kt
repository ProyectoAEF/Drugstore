package com.example.drugstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
class Menu : AppCompatActivity() {
    private lateinit var buy:ImageView
    private lateinit var log_out:ImageView
    private lateinit var mFirebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        buy=findViewById(R.id.buy)
        log_out=findViewById(R.id.log_out)
        mFirebaseAuth= FirebaseAuth.getInstance()
        buy.setOnClickListener {
            startActivity(Intent(this,Tienda::class.java))
        }
    }
    fun logout(view: View){
        logoutuser()
    }

    private fun  logoutuser(){
        mFirebaseAuth.signOut()
        finish()
        startActivity(Intent(this,LoginActivity::class.java))



    }
}