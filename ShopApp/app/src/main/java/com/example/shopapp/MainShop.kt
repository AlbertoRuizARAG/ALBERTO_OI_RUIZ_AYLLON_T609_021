package com.example.shopapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.databinding.ActivityMainShopBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException


class MainShop : AppCompatActivity() {

    private lateinit var binding: ActivityMainShopBinding

    private val list = ArrayList<CategoriesItem>()

    private val TAG = "MainShopTAGInfo"
    private var JSON_CATEGORIES_FILENAME = "jsonProductCategories.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainShopBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showInfoNotes()
        createUI()

    }

    private fun addNewProduct () {
        binding.addNewProduct.setOnClickListener {
            Toast.makeText(this@MainShop, "itemClicked:NEW PRODUCT", Toast.LENGTH_SHORT).show()

            // setup the alert builder
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose some animals")

            // add a checkbox list
            var sizeArray = list.count()
            var listItems: Array<String?> = arrayOfNulls<String>(sizeArray)

            for ((index, value) in list.withIndex()) {
                listItems[index] = value.textDefinition
            }

            val mBuilder = AlertDialog.Builder(this)
            mBuilder.setTitle("Choose a Category to Add New Product")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                val intent = Intent(this, AddNewProduct::class.java).apply {
                    putExtra("TipoCategoria",listItems[i].toString())
                }
                startActivity(intent)
            //txtView.text = listItems[i]
                //dialogInterface.dismiss()
            }
            // Set the neutral/cancel button click listener
            mBuilder.setNeutralButton("Cancel") { dialog, which ->
                // Do something when click the neutral button
                dialog.cancel()
            }

            val mDialog = mBuilder.create()
            mDialog.show()


        }

    }

    private fun showInfoNotes() {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("1. Conexíon a base de datos FIRESBASE TIEMPO REAL.\r\n" +
                "2.En el botón +, podemos crear nuevos productos que se almacenarán en la categoría " +
                "seleccionada.\r\n" +
                "3. Almacenaje de productos en FIREBASE\r\n" +
                "4. Futuras implementaciones:\n" +
                "   - Control de stock.\n" +
                "   - Avisos falta de producto.\n" +
                "   - Aplicación automática de ofertas.\n")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton(getString(R.string.boton_aceptar), DialogInterface.OnClickListener {
                    dialog, id ->

            })


        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("NEWS")
        // show alert dialog
        alert.show()

    }
    private fun createUI() {
        //cargar el fichero JSON con las categorias
        val jsonCategoryString = jsonParserDataToString(this, JSON_CATEGORIES_FILENAME)
        if (jsonCategoryString != null) {
            Log.i(TAG, jsonCategoryString)

            var jsonObject = JSONObject()
            try {
                jsonObject = JSONTokener(jsonCategoryString).nextValue() as JSONObject

            } catch (I: IOException) {
                Log.i(TAG, I.cause.toString())
            }

            val jsonArray = jsonObject.getJSONArray("categories")


            Log.i(TAG, jsonArray.length().toString())
            for (i in 0 until jsonArray.length()) {
                val imagen = jsonArray.getJSONObject(i).getString("Imagen")
                val isEnabled = jsonArray.getJSONObject(i).getBoolean("isEnabled")
                val tipo = jsonArray.getJSONObject(i).getString("tipo")

                val resourceID = resources.getIdentifier(imagen, "drawable", packageName)
                //val resourceID = resources.getIdentifier(imagen, assets.toString(), packageName)
                Log.i(TAG, resourceID.toString())
                val newItem = CategoriesItem(resourceID, tipo, isEnabled)
                list.add(newItem)

            }
            val adapter = CategoriesAdapter(list)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
      //      binding.recyclerView.setHasFixedSize(true)

            addNewProduct()

            adapter.setOnItemClickListener(object : CategoriesAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(this@MainShop, "itemClicked:$position", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@MainShop, MainViewProducts::class.java).apply {
                        putExtra("TipoCategoria",list[position].textDefinition.toString())
                    }
                    startActivity(intent)
                }
       /*             val db = Firebase.firestore
                    //add products
                    val nProd = Product("leche", "---------", "Lacteos", 100, 50, 1.47, 0, false)
                    val newProduct = hashMapOf(
                        "cantidadActual" to 1000,
                        "price" to 1.47,
                        "isPromocion" to true,
                        "discount"  to 0,
                        "descProducto" to "leche entera la asturiana",
                        "cantidadMin" to 100,
                        "categoriaID" to 0,
                        "nombreProducto" to "leche"
                    )
*/
                    // Add a new document with a generated ID
          /*          db.collection("productos")
                        .add(nProd)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                    db.collection("productos").whereEqualTo("nombre", "leche")
                        .get().addOnSuccessListener { result->
                            for (document in result) {
                                Log.d(TAG, "${document.id} => ${document.data}")
                            }
                        }
*/

                

            })


        }

    }
}