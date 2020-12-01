package com.example.drugstore.gestion_usuario

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class GetAge {
     @RequiresApi(Build.VERSION_CODES.O)
     fun diff(year: Int, mes: Int, dia: Int):Long{
         val start: LocalDate = LocalDate.of(
             year,
             mes,
             dia
         )
         val stop: LocalDate = LocalDate.now(ZoneId.of("America/Bogota"))
         val years = ChronoUnit.YEARS.between(start, stop)

         return  years;
    }


}

