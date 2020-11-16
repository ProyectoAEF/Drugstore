package com.example.drugstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Update_user : AppCompatActivity() {

    private lateinit var textnombre:EditText
    private lateinit var textapellido:EditText
    private lateinit var textnumid:EditText
    private  lateinit var textemail:EditText
    private lateinit var textpassword:EditText
    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    private lateinit var mAuth: FirebaseAuth
    private val mDatabase= FirebaseDatabase.getInstance().getReference("User")
    private val user=FirebaseAuth.getInstance().currentUser
    private val userid= user?.uid.toString()
    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthStateListener)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        val actionBar=supportActionBar
        actionBar?.hide()
        textnombre=findViewById(R.id.textnombre)
        textapellido=findViewById(R.id.textapellido)
        textnumid=findViewById(R.id.textnumid)
        textemail=findViewById(R.id.textemail)
        textpassword=findViewById(R.id.textpassword)
        mAuth=FirebaseAuth.getInstance()


        mAuthStateListener=FirebaseAuth.AuthStateListener{
            if(user==null){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                mDatabase.child(userid).addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            textnombre.setText(snapshot.child("Name").getValue().toString())
                            textapellido.setText(snapshot.child("Last Name").getValue().toString())
                            textnumid.setText(snapshot.child("Num ID").getValue().toString())
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }

    }

     fun eliminar_cuenta(view:View){

        user?.delete()?.addOnCompleteListener{ task->
            if(task.isComplete){
                finish()
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this,"Usuario eliminado",Toast.LENGTH_LONG).show()
            }else{
                finish()
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this,"Error al eliminar",Toast.LENGTH_LONG).show()
            }
        }
    }

     fun actu_nombre(view:View){
         mDatabase.child(userid).child("Name").setValue(textnombre.text.toString()).addOnCompleteListener { task->
             if (task.isComplete){
                 Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
             }else{
                 Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
             }
         }
    }



     fun actu_apellido(view:View){
        mDatabase.child(userid).child("Last Name").setValue(textapellido.text.toString()).addOnCompleteListener { task->
            if (task.isComplete){
                Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }

    }

     fun actu_numid(view:View){
         mDatabase.child(userid).child("Num ID").setValue(textnumid.text.toString()).addOnCompleteListener { task->
             if (task.isComplete){
                 Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
             }else{
                 Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
             }
         }

    }

     fun actu_email(view:View){
        val newemail:String=textemail.text.toString()
         user!!.updateEmail(newemail).addOnCompleteListener {task->
             if (task.isSuccessful){
                 finish()
                 startActivity(Intent(this,LoginActivity::class.java))
                 Toast.makeText(this, "Email actualizado", Toast.LENGTH_LONG).show()
             }else{
                 Toast.makeText(this, "Error al actualizar", Toast.LENGTH_LONG).show()
                 finish()
                 startActivity(Intent(this, LoginActivity::class.java))
             }
         }
    }


    fun actu_password(view:View) {
        val newpass: String = textpassword.text.toString()
        if (TextUtils.isEmpty(newpass) || newpass.length < 6) {
                    textpassword.setError("La contraseña minima es de 6 caracteres")
        }else{
            user!!.updatePassword(newpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Constraseña actualizada", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, "Error al actualizar", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }


}