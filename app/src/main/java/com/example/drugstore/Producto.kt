package com.example.drugstore

data class Producto (var imageUrl:String = "DEFAULT URL", var nombre:String = "DEFAULT NOMBRE", val precio:String = "DEFAULT PRECIO", val cantidad:String = "DEFAULT CANTIDAD", val prescrip:Boolean = false)