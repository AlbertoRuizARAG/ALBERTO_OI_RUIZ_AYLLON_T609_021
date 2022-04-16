package com.prilux.cmr.viewer


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import ble.libraries.UuidConstants
import com.prilux.cmr.MainViewCmr
import com.prilux.cmr.MenusAdapter
import com.prilux.cmr.MenusItem
import com.prilux.cmr.R
import com.prilux.cmr.databinding.ActivityMainBleOptionsBinding
import com.prilux.cmr.global.CmrBleActiveDevice
import converters.DateFunctions
import kotlin.collections.ArrayList


class CmrMainBleMenu : AppCompatActivity() {

    enum class MenuCases {
        SECUENCIAS,
        INFORMES,
        COMUNICACIONES,
        SEGURIDAD,
        AVANZADO,
        RELOJ_ASTRONOMICO
    }

    private lateinit var binding: ActivityMainBleOptionsBinding

    private val list = ArrayList<MenusItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBleOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list.add(MenusItem(getString(R.string.secuencias), getString(R.string.def_secuencias), "settings"))
        list.add(MenusItem(getString(R.string.informes), getString(R.string.def_informes), "informes"))
        list.add(MenusItem(getString(R.string.comunicaciones), getString(R.string.def_comunicaciones), "net"))
        list.add(MenusItem(getString(R.string.seguridad), getString(R.string.def_seguridad), "dado"))
        list.add(MenusItem(getString(R.string.avanzado), getString(R.string.def_avanzado), "maintenance"))
        list.add(MenusItem(getString(R.string.reloj_astronomico), getString(R.string.def_reloj_astronomico), "programs"))

        setUI(list)
    }

    override fun onResume() {
        super.onResume()
        setUI(list)
        ConnectionManager.registerListener(connectionEventListener)
        syncCalendar()
    }

    override fun onDestroy() {
        super.onDestroy()
        closeALlReferences()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        closeALlReferences()

        val intent = Intent(this, MainViewCmr::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)

    }

    private fun closeALlReferences() {
        //Unregister callbacks
        ConnectionManager.unregisterListener(connectionEventListener)
        //Cierro conexion del BLE
        if(CmrBleActiveDevice.device != null ) {
            ConnectionManager.teardownConnection(CmrBleActiveDevice.device!!)
        }
        CmrBleActiveDevice.clearObject()

    }
    private fun setUI(products: ArrayList<MenusItem>) {

        val adapter = MenusAdapter(products, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : MenusAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@CmrMainBleMenu , "itemClicked:$position", Toast.LENGTH_SHORT)
                    .show()

                //selecciono la celda cogiendo la posicion real del adapter
                adapter.setCellPosition(position)

                when (position) {
                    MenuCases.SECUENCIAS.ordinal -> print(" es 1")
                    MenuCases.INFORMES.ordinal -> print("es 2")
                    MenuCases.COMUNICACIONES.ordinal -> {
                        val intent = Intent(this@CmrMainBleMenu, CmrViewerActivity::class.java)
                        this@CmrMainBleMenu.startActivity(intent)
                    }
                    MenuCases.SEGURIDAD.ordinal -> {
                     //   val intent = Intent(this@MainViewCmr, CmrSearcherNearbyDevices::class.java)
                     //   this@MainViewCmr.startActivity(intent)
                    }

                    MenuCases.AVANZADO.ordinal ->print(" esMANT")
                    MenuCases.RELOJ_ASTRONOMICO.ordinal -> print("es REL")
                    else -> {

                    }

                }


            }
        })
    }
    private fun syncCalendar() {

        //     val otaServiceCheck = CmrBleActiveDevice.deviceGatt?.getService(UuidConstants.BLE_SERVICE_CurrentTime) != null
        val toRead = CmrBleActiveDevice.deviceGatt?.getService(UuidConstants.BLE_SERVICE_RelojCrepuscular)?.getCharacteristic(UuidConstants.BLE_CHARACTERISTIC_CREPUSCULAR_HORA_ACTUAL_RELOJ_ASTRONOMICO)

        toRead.let {
            CmrBleActiveDevice.deviceGatt.let {
                CmrBleActiveDevice.device.let {
                    ConnectionManager.readCharacteristic(CmrBleActiveDevice.deviceGatt!!.device, toRead!!)
                }
            }
        }

        val toWrite = CmrBleActiveDevice.deviceGatt?.getService(UuidConstants.BLE_SERVICE_CURRENT_TIME)?.getCharacteristic(UuidConstants.BLE_CHARACTERISTIC_CurrentTime)
        toWrite.let {
            CmrBleActiveDevice.deviceGatt.let {
                CmrBleActiveDevice.device.let {
                    ConnectionManager.writeCharacteristic(CmrBleActiveDevice.deviceGatt!!.device, toWrite!!, DateFunctions.getDataTimeToBLE())
                }
            }
        }
    }
    /*******************************************
     * CORE BLE functions
     *******************************************/
    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {
            onCharacteristicRead = { device, charactetistic ->
                if (charactetistic.uuid == UuidConstants.BLE_CHARACTERISTIC_CREPUSCULAR_HORA_ACTUAL_RELOJ_ASTRONOMICO) {
                    val LEIDOOOOORRRRR = charactetistic.value
                    val string = String(LEIDOOOOORRRRR, Charsets.UTF_8)
                    runOnUiThread {
                        Toast.makeText(
                            this@CmrMainBleMenu,
                            ("LEIDO BLE $string"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            onCharacteristicWrite = { device, characteristic ->
                if (characteristic.uuid == UuidConstants.BLE_CHARACTERISTIC_CurrentTime) {
                    runOnUiThread {
                        Toast.makeText(
                            this@CmrMainBleMenu,
                            (getString(R.string.calendario_actualizado)),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            onDisconnect = {
                runOnUiThread {
                    val alertDialogBuilder = AlertDialog.Builder(applicationContext)
                    alertDialogBuilder.setTitle("Disconnected")
                    alertDialogBuilder.setMessage("Disconnected or unable to connect to device.")
                    alertDialogBuilder.setPositiveButton(com.prilux.biblioteca.R.string.btn_aceptar ) { _, _ ->

                    }
                    alertDialogBuilder.show()
                }
            }
        }
    }

}