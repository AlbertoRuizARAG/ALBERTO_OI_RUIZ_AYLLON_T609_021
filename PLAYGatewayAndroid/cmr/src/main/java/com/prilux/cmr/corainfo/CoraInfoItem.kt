package com.prilux.cmr.corainfo

data class CoraInfoItem(
    val nombre: String? = "",
    val coraID: String? = "",
    val addressMap: String? = "",
    var isSelected: Boolean? = false
)