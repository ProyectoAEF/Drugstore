package com.example.drugstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity() {
    private lateinit var editTextTextEmailAddress2:EditText
    private lateinit var auth:FirebaseAuth
    private  lateinit var  progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        editTextTextEmailAddress2=findViewById(R.id.editTextTextEmailAddress2)

        progressBar=findViewById(R.id.progressBar)

        auth=FirebaseAuth.getInstance()
    }

    fun send(view:View){
        val email=editTextTextEmailAddress2.text.toString()

        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this){
                    task->

                    if(task.isSuccessful){
                        progressBar.visibility=View.VISIBLE
                        startActivity(Intent(this,MainActivity::class.java))
                    }else{
                        Toast.makeText(this,"Error al enviar Email",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}