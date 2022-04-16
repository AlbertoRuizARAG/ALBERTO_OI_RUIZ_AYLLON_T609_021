package com.prilux.cmr

data class MenusItem(
    val nombre: String? = "",
    val descripcion: String? = "",
    val imagenURL: String? = "",
    var isSelected: Boolean? = false)