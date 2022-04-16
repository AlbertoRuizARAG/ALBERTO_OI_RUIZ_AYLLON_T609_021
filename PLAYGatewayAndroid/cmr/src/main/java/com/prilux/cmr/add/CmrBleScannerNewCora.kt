package com.prilux.cmr.add

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ble.ScanResultAdapter
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import ble.libraries.toHexString
import com.prilux.biblioteca.R
import com.prilux.cmr.MainViewCmr
import com.prilux.cmr.databinding.ActivityCmrBleScannerBinding
import com.prilux.cmr.global.CmrBleActiveDevice
import com.prilux.cmr.globals.DbConstants
import com.prilux.cmr.globals.parseCoraNamePlatformToBle
import global.login.LoginGlobalCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.openapitools.client.apis.CoraApi
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.CoraListDtoListResultDto
import java.io.IOException


class CmrBleScannerNewCora : AppCompatActivity() {
    private val TAG = "BLE ADD SCANNER ACTIVITY"

    private lateinit var binding: ActivityCmrBleScannerBinding
    companion object {
        private const val PERMISSION_BLE = 100
        private const val ENABLE_BLUETOOTH_REQUEST_CODE = 1
        private const val LOCATION_PERMISSION_REQUEST_CODE = 2
    }

    private var device: BluetoothDevice? = null
    private var deviceGatt: BluetoothGatt? = null

    private var coraList = CoraListDtoListResultDto()

    /*******************************************
     * Properties
     *******************************************/

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private var isScanning = false
        set(value) {
            field = value
            runOnUiThread {
                val idButton = findViewById<Button>(R.id.scan_button)
                idButton.text = if (value) "Stop Scan" else "Start Scan"
            }
        }

    private val scanResults = mutableListOf<ScanResult>()

    //Extraemos el evento del adapter del item para la conexion
    private val scanResultAdapter: ScanResultAdapter by lazy {
        ScanResultAdapter(scanResults) { result ->
            if (isScanning) {
                stopBleScan()
            }
            with(result.device) {
                Log.w(TAG,"Connecting to $address")
                Toast.makeText(this@CmrBleScannerNewCora, (getString(R.string.conectando_a_dispositivo) + ": $address"), Toast.LENGTH_SHORT).show()

                ConnectionManager.connect(this, this@CmrBleScannerNewCora)

            }
        }
    }

   /* private val isLocationPermissionGranted
        get() = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
*/
    /*******************************************
     * Activity function overrides
     *******************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCmrBleScannerBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        // Descargar Coras Cloud
        getCoraCloud()

        if(coraList.items != null) {
            setupRecyclerView()
            // Verificar los Permisos
            verifyLocationPermissions()
            // Crear los listener a callbacks
            ConnectionManager.registerListener(connectionEventListener)
        }
    }

    private fun getCoraCloud(){
        runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                try {
                    val cora = CoraApi(LoginGlobalCredentials.basePathUrl)
                    coraList = cora.apiServicesAppCoraGetCloudCorasGet()
                    Log.e(TAG, ("$coraList"))
                } catch (e: ServerException) {
                    Log.e(TAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                } catch (e: ClientException) {
                    Log.e(TAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                } catch (e: IOException) {
                    Log.e(TAG, ("[${e.message.toString()}"))
                }
            }
            job.join()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopBleScan()
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
        //Clean object BLE
        CmrBleActiveDevice.clearObject()
    }
    /*******************************************
     * Private functions
     *******************************************/
    @SuppressLint("MissingPermission")
    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            bluetoothAdapter.enable()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startBleScan() {
        if (bluetoothAdapter.isEnabled) {
            scanResults.clear()
            scanResultAdapter.notifyDataSetChanged()
            bleScanner.startScan(null, scanSettings, scanCallback)
            isScanning = true
        } else {
            promptEnableBluetooth()
        }
    }

    private fun verifyLocationPermissions() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
        } else{
            verifyBLEApadterPermissions()
        }

    }

    private fun verifyBLEApadterPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
                requestPermissions(permissions, ENABLE_BLUETOOTH_REQUEST_CODE)
            } else {
                verifyBLEPermissions()
            }
        } else {
            if(!bluetoothAdapter.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                requestBluetooth.launch(enableBtIntent)
            } else {
                verifyBLEPermissions()
            }
        }
    }

    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            verifyBLEPermissions()
        }else{
            Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun verifyBLEPermissions() {
         if (checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED  ||
             checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_DENIED
            ) {
            val permissions = arrayOf(
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH)
            requestPermissions(permissions, PERMISSION_BLE)
        } else{
            startBleScan()
        }

    }
    //Control de Permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(this,"$requestCode", Toast.LENGTH_SHORT).show()
        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    verifyBLEApadterPermissions()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            ENABLE_BLUETOOTH_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    verifyBLEPermissions()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            PERMISSION_BLE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startBleScan()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }
    }

    @SuppressLint("MissingPermission")
    private fun stopBleScan() {
        bleScanner.stopScan(scanCallback)
        isScanning = false
    }

    private fun setupRecyclerView() {
        val recyclerId: RecyclerView = findViewById(R.id.scan_results_recycler_view)
        recyclerId.apply {
            adapter = scanResultAdapter
            layoutManager = LinearLayoutManager(
                this@CmrBleScannerNewCora,
                RecyclerView.VERTICAL,
                false
            )
            isNestedScrollingEnabled = false
        }

        val animator = recyclerId.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    /*******************************************
     * Callback
     *******************************************/

    private val scanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val indexQuery = scanResults.indexOfFirst   { it.device.address == result.device.address }
            if (indexQuery != -1) { // A scan result already exists with the same address
                scanResults[indexQuery] = result
                scanResultAdapter.notifyItemChanged(indexQuery)
            } else {
                with(result.device) {
                    Log.i(TAG, "Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                }
                if (result.device.name != null) {

                    val nameToSearch = parseCoraNamePlatformToBle(result.device.name, DbConstants.MODEL_CMR_NAME)
                    coraList.items!!.filter { it.coraIdStr == nameToSearch }.forEach {
                       return
                    }
                    // si no salta el return anterior , añade el dispositivo
                    scanResults.add(result)
                    scanResultAdapter.notifyItemInserted(scanResults.size - 1)
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "onScanFailed: code $errorCode")
        }
    }

    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {

            onNotificationsEnabled = {device, char ->

            }
            onCharacteristicChanged = {device, char ->


                Log.i(TAG, "onCharacteristicChanged: ${char.value.toHexString() }")
            }
            onConnectionSetupComplete = { gatt ->
                stopBleScan()

                device = gatt.device
                deviceGatt = gatt

                // Eliminar el registro del listener
                ConnectionManager.unregisterListener(this)

                // Asiganar las Globales del BLE
                CmrBleActiveDevice.device = gatt.device
                CmrBleActiveDevice.deviceGatt = gatt

                // Arrancar pantalla principal BLE - Options
               Intent(this@CmrBleScannerNewCora , CmrAddNewCora::class.java).also {
                  //  it.putExtra(BluetoothDevice.EXTRA_DEVICE, gatt.device)
                    startActivity(it)
                }


            }
            onCharacteristicRead = { device, charactetistic ->
                val leiDOOOOO = charactetistic.value
                val string = String(leiDOOOOO, Charsets.UTF_8)
                runOnUiThread {
                    Toast.makeText(this@CmrBleScannerNewCora, ("LEIDO BLE $string"), Toast.LENGTH_SHORT)
                        .show()
                }
            }
            onDisconnect = {
                runOnUiThread {
                    val alertDialogBuilder = AlertDialog.Builder(applicationContext)
                    alertDialogBuilder.setTitle("Disconnected")
                    alertDialogBuilder.setMessage("Disconnected or unable to connect to device.")
                    alertDialogBuilder.setPositiveButton(R.string.btn_aceptar ) { _, _ ->

                    }
                    alertDialogBuilder.show()
                }
            }
        }
    }

}
