package com.example.drugstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.utilities.Utilities
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextTextEmailAddress:EditText
    private lateinit var editTextTextPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar=supportActionBar
        actionBar?.hide()

        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword=findViewById(R.id.editTextTextPassword)
        progressBar=findViewById(R.id.progressBar3)
        auth= FirebaseAuth.getInstance()
    }
    fun login(view:View){
        loginUser()
    }

    fun forgotpassword(view:View){
        startActivity(Intent(this,ForgotPassActivity()::class.java))
    }

    fun register(view: View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    private fun loginUser(){
        val user:String=editTextTextEmailAddress.text.toString()
        val password:String=editTextTextPassword.text.toString()

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
            progressBar3.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                        task->

                    if(task.isSuccessful){
                        action()
                    }else{
                        Toast.makeText(this,"Error en la autenticacion",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun action(){
        finish()
        val mDatabase= FirebaseDatabase.getInstance().getReference("User")
        val user=FirebaseAuth.getInstance().currentUser
        val userid= user?.uid.toString()
        val intent = Intent(this, TiendaP::class.java).apply { putExtra("cliente", userid) }
        startActivity(intent)
    }
}