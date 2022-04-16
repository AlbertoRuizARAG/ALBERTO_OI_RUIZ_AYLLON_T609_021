package com.prilux.cmr.add

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import cell.ItemTextValue
import cell.TextValueAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.prilux.cmr.R
import com.prilux.cmr.databinding.ActivityCmrAddNewCoraBinding
import com.prilux.cmr.global.CmrBleActiveDevice
import com.prilux.cmr.globals.DbConstants
import com.prilux.cmr.globals.parseCoraNameBleToPlatform
import com.prilux.cmr.globals.parseCoraNamePlatformToBle
import global.login.LoginGlobalCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.openapitools.client.apis.CoraApi
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.CoraListDto
import org.openapitools.client.models.CoraState
import org.openapitools.client.models.Coratype
import java.io.IOException
import java.time.OffsetDateTime
import java.util.*

class CmrAddNewCora : AppCompatActivity(), OnMapReadyCallback {
    private val TAG = "CMR_ADD"
    private val USERLOCATION = "ME"
    private lateinit var mMap: GoogleMap
    private var userMarkerMap: Marker? = null
    private lateinit var binding: ActivityCmrAddNewCoraBinding

    lateinit var locationManager: LocationManager
    private lateinit var userLocation: Location
    lateinit var gpsLocationListener: LocationListener
    lateinit var networkLocationListener: LocationListener

    val list = ArrayList<ItemTextValue>()

    var coraNameBle = ""

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCmrAddNewCoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1 - Verificar que los permisos estan concedidos
        verificarPermisos()

        //2- Cargar la lista de datos
        CmrBleActiveDevice.let {
            it.device?.let {
                coraNameBle = parseCoraNameBleToPlatform(it.name, DbConstants.MODEL_CMR_NAME)
            }
        }

        list.add(ItemTextValue(getString(R.string.alias), ""))
        list.add(ItemTextValue(getString(R.string.mac_address), coraNameBle))
        list.add(ItemTextValue(getString(R.string.calle), ""))
        list.add(ItemTextValue(getString(R.string.ciudad), ""))
        setUI(list)


    }

    private fun setUI(lista: ArrayList<ItemTextValue>) {
        val adapter = TextValueAdapter(lista, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object: TextValueAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                if (position == 0) {
                   showdialog()

                }
                Toast.makeText(this@CmrAddNewCora , "item:$position dispositivo seleccionado", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        binding.saveFav.setOnClickListener {
            storeCoraCloud()
        }

    }

    private fun showdialog(){
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.cambiar_nombre))

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint(getString(R.string.nuevo_nombre))
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton(getString(R.string.btn_aceptar), DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = input.text.toString()

            list!!.filter { it.text == getString(R.string.alias) }.forEach { it.value = m_Text }
            setUI(list)
        })
        builder.setNegativeButton(getString(R.string.btn_cancelar), DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    private fun procesoOkDialog(){
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        //builder.setTitle(getString(R.string.cambiar_nombre))
        builder.setTitle("ALMACENAJE CLOUD")

        // Set up the buttons
        builder.setPositiveButton(getString(R.string.btn_aceptar), DialogInterface.OnClickListener { dialog, which ->
         //TODO: Intent
        })

        builder.show()
    }

    private fun procesoFailDialog(message: String){
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        //builder.setTitle(getString(R.string.cambiar_nombre))
        builder.setTitle(message)

        // Set up the buttons
        builder.setPositiveButton(getString(R.string.btn_aceptar), DialogInterface.OnClickListener { dialog, which ->
            //TODO: Intent
        })

        builder.show()
    }

    private fun storeCoraCloud(){
        //Parametros de Geoloc
        val latitude = userLocation.latitude.toString()
        val longitude = userLocation.longitude.toString()
        if (latitude.isEmpty() || longitude.isEmpty()) {
            procesoFailDialog("Fallo GEOLOC")
            return
        }
        // ALias del Cora
        val coraDescription = list.get(0).value
        if (coraDescription != null) {
            if (coraDescription.isEmpty()) {
                procesoFailDialog("Fallo Alias")
                return
            }
        }
       runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                try {
                    val newCora: CoraListDto = CoraListDto(0, coraDescription, OffsetDateTime.now(), CoraState._0,
                    0, coraNameBle, latitude, longitude, Coratype._0,  OffsetDateTime.now(),
                    null, null)

                    val cora = CoraApi(LoginGlobalCredentials.basePathUrl)
                    val response = cora.apiServicesAppCoraAddCoraDevicePost(newCora)
                    if(response.successFul == true) {
                        // TODO: METER MENSAJES + INTENTS + ALMACENAR BLE + LAT LONG + HORA + xtradata
                        procesoOkDialog()
                    } else {
                        //TODO: METER ERRORES
                        procesoFailDialog("Fallo Cloud")
                    }

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

    private fun launchMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // PARA coger la lat y long al presionar en el mapa
    private fun setUpMap() //If the setUpMapIfNeeded(); is needed then...
    {
        mMap.setOnMapClickListener {
            it.latitude
            it.longitude

            createMarker(it.latitude, it.longitude, USERLOCATION, true, true)

            Toast.makeText(this , "Nuestra LOC:$it.latitude, $it.longitude", Toast.LENGTH_SHORT)
                .show()
            // Paramos geolocalizacion Automatica
            stopLocation()
        }
        mMap.setOnMapLongClickListener{

        }
    }

    private fun stopLocation() {
        locationManager.removeUpdates(gpsLocationListener)
        locationManager.removeUpdates(networkLocationListener)
    }
    //Suprime los permisos porque los pido antes
    //https://www.geeksforgeeks.org/how-to-get-current-location-in-android/
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        gpsLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                userLocation = location

                createMarker(
                        userLocation.latitude,
                        userLocation.longitude,
                        USERLOCATION,
                        true,
                        true
                    )
                // Paro las actualizaciones de Geolocalizacion
                stopLocation()

            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        networkLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                userLocation = location

                createMarker(
                        userLocation.latitude,
                        userLocation.longitude,
                        USERLOCATION,
                        true,
                        true
                    )
                // Paro las actualizaciones de Geolocalizacion
                stopLocation()

            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener
            )
        }
        // MAS RAPIDO
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener
            )


            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {
                userLocation = location
                createMarker(userLocation.latitude, userLocation.longitude, USERLOCATION, true, false)
            }

        }
    }

    private fun getAddressInfo(latitude:Double, longitude:Double){
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)

        val address: String = addresses[0].getAddressLine(0)
        val city: String = addresses[0].locality
        val state: String = addresses[0].adminArea
        val country: String = addresses[0].countryName
        val postalCode: String = addresses[0].postalCode
        val knownName: String = addresses[0].featureName

        list!!.filter { it.text == getString(R.string.calle) }.forEach { it.value = address }
        list!!.filter { it.text == getString(R.string.ciudad) }.forEach { it.value = city }

        setUI(list)

    }

    private fun createMarker(latitude: Double, longitude: Double, coraName: String, zoom: Boolean, isUserLocation: Boolean) {
        // Ya que solo necesitamos un marker borramos todos
        mMap.clear()
        val favoritePlace = LatLng(latitude,longitude)
        val marker =  mMap.addMarker(MarkerOptions().position(favoritePlace).title(coraName))

        if (isUserLocation) {
            if (userMarkerMap != null) {
                userMarkerMap?.remove()
            }
            if (marker != null) {
                userMarkerMap = marker
            }
        }
        // mMap.addMarker(MarkerOptions().position(favoritePlace).title(coraName))
        if (zoom) {
            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f),
                2000,
                null
            )
        }

        getAddressInfo(latitude, longitude)

    }
    private fun verificarPermisos() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED  ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            requestPermissions(permissions, PERMISSION_REQUEST_ACCESS_LOCATION)
        } else{
            launchMap()
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
            PERMISSION_REQUEST_ACCESS_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    launchMap()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // configurar el TAP manual en mapa
        setUpMap()
        // Coger la geoloc. actual
        getLocation()

    }
}

