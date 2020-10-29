package com.example.drugstore

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextTextPersonName6:EditText
    private lateinit var editTextTextPersonName5:EditText
    private lateinit var editTextTextPersonName4:EditText
    private lateinit var editTextTextEmailAddress3:EditText
    private lateinit var editTextTextPassword3:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextTextPersonName6=findViewById(R.id.editTextTextPersonName6)
        editTextTextPersonName5=findViewById(R.id.editTextTextPersonName5)
        editTextTextPersonName4=findViewById(R.id.editTextTextPersonName4)
        editTextTextEmailAddress3=findViewById(R.id.editTextTextEmailAddress3)
        editTextTextPassword3=findViewById(R.id.editTextTextPassword3)

        progressBar= findViewById(R.id.progressBar2)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbReference=database.reference.child("User")


    }

    fun register(view: View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name:String=editTextTextPersonName6.text.toString()
        val lastname:String=editTextTextPersonName5.text.toString()
        val numid:String=editTextTextPersonName4.text.toString()
        val email:String=editTextTextEmailAddress3.text.toString()
        val password:String=editTextTextPassword3.text.toString()

        if(TextUtils.isEmpty(password)||password.length<6){
            editTextTextPassword3.setError("La contraseña minima es de 6 caracteres")
        }else{
            if(isValidEmail(email.trim())){
                editTextTextEmailAddress3.setError("Email con caracteres no validos")
            }else{
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(lastname)&&!TextUtils.isEmpty(numid)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)){
                    progressBar.visibility=View.VISIBLE

                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){
                                task->

                            if(task.isComplete){
                                val user:FirebaseUser?=auth.currentUser
                                verifyEmail(user)

                                val userBD= user?.uid?.let { dbReference.child(it) }
                                userBD?.child("Name")?.setValue(name)
                                userBD?.child("Last Name")?.setValue(lastname)
                                userBD?.child("Num ID")?.setValue(numid)
                                action()
                            }
                        }


                }
        }
        }




}
    private fun action(){
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){ task->

                if(task.isComplete){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al registrarse", Toast.LENGTH_LONG).show()
                }
            }
    }

}

private fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}