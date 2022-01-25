package com.example.shopapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.databinding.ActivityMainShopBinding
import com.example.shopapp.databinding.ActivityMainViewProductsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewProducts : AppCompatActivity() {

    private lateinit var binding: ActivityMainViewProductsBinding

    private val list = ArrayList<ProductsItem>()

    private val TAG = "MainViewProductsTAGInfo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainViewProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intentValue = intent.getStringExtra("TipoCategoria").toString()

        getDataBaseProducts(intentValue)



    }

    private fun loadProducts(products: ArrayList<ProductsItem>) {


        val adapter = ProductsAdapter(products)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : ProductsAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainViewProducts , "itemClicked:$position", Toast.LENGTH_SHORT)
                    .show()
            }
            })
    }
    private fun getDataBaseProducts(categoria: String) {
        val db = Firebase.firestore

      //  val list =  ArrayList<ProductsItem>()

        db.collection("productos").whereEqualTo("categoriaId", categoria)
            .get().addOnSuccessListener { result->
              //  val items =  ArrayList<ProductsItem>()
                for (document in result) {

                    val cluster = document.toObject(ProductsItem::class.java)
                 ///   items.add(cluster)
                    list.add(cluster)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }

                //list.add(items)
                loadProducts(list)

            }
    }
}