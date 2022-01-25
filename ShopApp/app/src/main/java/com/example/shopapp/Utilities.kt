package com.example.shopapp


import android.content.Context
import java.io.IOException


//Parseo de ficheros JSON a string.
fun jsonParserDataToString(context: Context, fileName: String): String? {
    var json_string: String
    try {
        json_string = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
    catch (I: IOException) {
        return null
    }
    return json_string
}