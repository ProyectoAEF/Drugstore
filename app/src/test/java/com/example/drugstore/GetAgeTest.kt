package com.example.drugstore

import org.junit.Assert.assertTrue
import org.junit.Test

class GetAgeTest{
    private val prueba=GetAge()

    @Test
    fun no_negative(){
      assertTrue(prueba.diff(2001,2,25)>0)
    }
}