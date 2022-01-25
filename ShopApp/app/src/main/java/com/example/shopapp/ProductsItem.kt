package com.example.shopapp

data class ProductsItem(val nombre: String? = "",
                        val descProducto: String? = "",
                        val categoriaId: String? = "",
                        val cantidadActual: Int? = 0,
                        val cantidadMin: Int? = 0,
                        val price: Double? = 0.0,
                        val discount: Int? = 0,
                        val promocion: Boolean? = false,
                        val imagenURL: String? = "")