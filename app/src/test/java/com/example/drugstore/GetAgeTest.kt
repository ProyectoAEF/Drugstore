package com.example.drugstore

import com.example.drugstore.gestion_usuario.GetAge
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAgeTest{
    private val prueba= GetAge()

    @Test
    fun no_negative(){
      assertTrue(prueba.diff(2001,2,25)>0)
    }
}