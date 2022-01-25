package com.example.shopapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.shopapp.databinding.ActivityAddNewProductBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNewProduct : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewProductBinding

    private val TAG = "AddNewProductTAGInfo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intentValue = intent.getStringExtra("TipoCategoria").toString()

        binding.categoria.setText(intentValue)

        setButtons()

        val db = Firebase.firestore

        //QUERY PARA TEST Y RECIBIR TODOS LOS PRODUCTOS DE LA CATEGORIA
        if( intentValue != null) {
            db.collection("productos").whereEqualTo("categoriaId", intentValue)
                .get().addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
        }

      //  addAllProductsTest()
    }

    private fun setButtons() {

        binding.precio.inputType = InputType.TYPE_CLASS_NUMBER
        binding.cantidadInsertada   .inputType = InputType.TYPE_CLASS_NUMBER
        binding.stockMinimo.inputType = InputType.TYPE_CLASS_NUMBER
        binding.descuento.inputType = InputType.TYPE_CLASS_NUMBER

        binding.accept.setOnClickListener {
            if  (binding.nombre.text.isEmpty()||
                binding.descripcion.text.isEmpty()||
                binding.cantidadInsertada.text.isEmpty() ||
                binding.stockMinimo.text.isEmpty()||
                binding.precio.text.isEmpty()||
                binding.descuento.text.isEmpty()) {

                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Empty Fields")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(getString(R.string.boton_aceptar), DialogInterface.OnClickListener {
                            dialog, id ->

                    })


                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Warning!!")
                // show alert dialog
                alert.show()

            } else {
              /*  try {
                    val dobl: Double? = binding.precio.text.toString().toDouble()
                }*/
                addNewProduct()
            }
        }
        binding.cancel.setOnClickListener {
            finish()
        }
    }

    private fun addAllProductsTest() {
        val db = Firebase.firestore
        val nProd = ArrayList<Product>()
        var pro1 = Product("Judias Verdes", "Rama", "Verduras",         123, 1000, 1.56, 0, false , "")
        var pro2 = Product("Tomates cherry", "Tomate Primera", "Verduras", 222, 1000, 1.30, 0, false,"" )
        var pro3 = Product("Espinacas", "Fresca", "Verduras",           33, 1000, 1.10 , 0, false,"" )
        var pro4 = Product("Puerros", "Pack", "Verduras",               129, 1000, 2.30, 0, false, "" )
        var pro5 = Product("Berenjena", "Pack", "Verduras",             234, 1000, 2.2, 0, false,"" )
        var pro6 = Product("Calabacín", "De cepa", "Verduras",           145, 1000, 1.6, 0, false ,"")
        var pro7 = Product("Brocoli", "Fresco", "Verduras",              146, 54, 2.50, 0, false,"" )
        var pro8 = Product("Coliflor", "Fresca", "Verduras",            150, 45, 1.50, 0, false, "")
        var pro9 = Product("Pimiento Rojo", "Asar", "Verduras",          150, 45, 3.50, 0, false,"" )
        var pro10 = Product("Pimiento verde", "Italiano", "Verduras",     200, 30, 3.30, 0, false,"" )
        nProd.add(pro1)
        nProd.add(pro2)
        nProd.add(pro3)
        nProd.add(pro4)
        nProd.add(pro5)
        nProd.add(pro6)
        nProd.add(pro7)
        nProd.add(pro8)
        nProd.add(pro9)
        nProd.add(pro10)

        for (i in nProd) {
            // Add a new document with a generated ID
            db.collection("productos")
                .add(i)
                .addOnSuccessListener { documentReference ->

                }
        }



    }

    private fun addNewProduct () {
        val db = Firebase.firestore

        val cantidadInsertada: Int = binding.cantidadInsertada.text.toString().toInt()
        val stockMin: Int = binding.stockMinimo.text.toString().toInt()
        val precio: Double = binding.precio.text.toString().toDouble().also { 0 }
        val descuento: Int = binding.descuento.text.toString().toInt()
        //add products
        val nProd = Product(
            binding.nombre.text.toString(),
            binding.descripcion.text.toString(),
            binding.categoria.text.toString(),
            cantidadInsertada,
            stockMin,
            precio,
            descuento,
            binding.promocion.isChecked,
            ""
        )



        // Add a new document with a generated ID
        db.collection("productos")
            .add(nProd)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Items stored en FIREBASE Database")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(getString(R.string.boton_aceptar), DialogInterface.OnClickListener {
                            dialog, id ->
                        binding.cantidadInsertada.text = null
                        binding.stockMinimo.text = null
                        binding.precio.text = null
                        binding.descuento.text = null
                        binding.nombre.text = null
                        binding.descripcion.text = null
                        binding.promocion.isChecked = false

                    })
                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("PROCESS OK!!")
                // show alert dialog
                alert.show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("NOT SUCESS")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(getString(R.string.boton_aceptar), DialogInterface.OnClickListener {
                            dialog, id ->

                    })
                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("ERROR!!")
                // show alert dialog
                alert.show()



            }
      /*  db.collection("productos").whereEqualTo("nombre", "leche")
            .get().addOnSuccessListener { result->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
        */
    }
}