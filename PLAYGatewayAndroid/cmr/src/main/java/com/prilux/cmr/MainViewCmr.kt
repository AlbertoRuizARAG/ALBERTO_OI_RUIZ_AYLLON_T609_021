package com.prilux.cmr


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prilux.cmr.add.CmrAddNewCora
import com.prilux.cmr.add.CmrBleScannerNewCora
import com.prilux.cmr.databinding.ActivityMainViewCmrBinding
import com.prilux.cmr.viewer.CmrViewerActivity


class MainViewCmr : AppCompatActivity() {

    enum class MenuCases {
        ADD,
        ACTIVATE,
        VIEWER,
        SEARCHER,
        MAINTENANCE
    }

    private lateinit var binding: ActivityMainViewCmrBinding

    private val list = ArrayList<MenusItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainViewCmrBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list.add(MenusItem(getString(R.string.ble_menu_añadir), getString(R.string.ble_menu_añadir_desc), "crear"))
        list.add(MenusItem(getString(R.string.ble_menu_activar), getString(R.string.ble_menu_activar_desc), "documento"))
        list.add(MenusItem(getString(R.string.ble_menu_visualizar), getString(R.string.ble_menu_visualizar_desc), "visualizar"))
        list.add(MenusItem(getString(R.string.ble_menu_buscador), getString(R.string.ble_menu_buscador_desc), "ibeacon"))
        list.add(MenusItem(getString(R.string.ble_menu_mantenimiento), getString(R.string.ble_menu_mantenimiento_desc), "maintenance"))

        setUI(list)
    }

    override fun onResume() {
        super.onResume()
        setUI(list)
    }
    private fun setUI(products: ArrayList<MenusItem>) {

        val adapter = MenusAdapter(products, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : MenusAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainViewCmr , "itemClicked:$position", Toast.LENGTH_SHORT)
                    .show()

                //selecciono la celda cogiendo la posicion real del adapter
                adapter.setCellPosition(position)

                when (position) {
                    MenuCases.ADD.ordinal -> {
                        val intent = Intent(this@MainViewCmr, CmrBleScannerNewCora::class.java)
                        this@MainViewCmr.startActivity(intent)
                    }
                    MenuCases.ACTIVATE.ordinal -> print("es 2")
                    MenuCases.VIEWER.ordinal -> {
                        val intent = Intent(this@MainViewCmr, CmrViewerActivity::class.java)
                        this@MainViewCmr.startActivity(intent)
                    }
                    MenuCases.SEARCHER.ordinal -> {
                     //   val intent = Intent(this@MainViewCmr, CmrSearcherNearbyDevices::class.java)
                     //   this@MainViewCmr.startActivity(intent)
                    }

                    MenuCases.MAINTENANCE.ordinal ->print(" esMANT")
                    else -> {

                    }
                }


            }
        })
    }

}