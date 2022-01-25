package com.example.shopapp

data class Product(

    //{cantidadActual=1000, price=1.47, isPromocion=true, discount=0, descProducto=leche entera la asturiana, cantidadMin=100, categoriaID=0, nombreProducto=leche}
    val nombre: String,
    var descProducto: String,
    var categoriaId: String,
    var cantidadActual: Int,
    var cantidadMin: Int,
    var price: Double,
    var discount: Int,
    var isPromocion: Boolean,
    var imagenURL: String?
  // var photos: List<Photo> = arrayListOf()
)
