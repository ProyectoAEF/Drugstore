package com.example.drugstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private  lateinit var mTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView=findViewById(R.id.mTextView)
        val mDatabase=FirebaseDatabase.getInstance().getReference("User")
        val user=FirebaseAuth.getInstance().currentUser
        val userid= user?.uid.toString()


        mDatabase.child(userid).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val cedula:String=snapshot.child("Num ID").getValue().toString()
                    mTextView.setText("La cedula del usuario es "+cedula);

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }


    }



