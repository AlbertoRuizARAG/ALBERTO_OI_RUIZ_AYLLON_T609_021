package com.prilux.cmr.viewer

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.prilux.cmr.R
import com.prilux.cmr.corainfo.CoraInfoAdapter
import com.prilux.cmr.corainfo.CoraInfoItem
import com.prilux.cmr.databinding.ActivityCmrViewerBinding
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
import java.util.*


class CmrViewerActivity : AppCompatActivity(), OnMapReadyCallback {
    private val LOGTAG = "CMR_VIEWER"
    private val USERLOCATION = "ME"
    private lateinit var mMap: GoogleMap
    private var userMarkerMap: Marker? = null
    private lateinit var binding: ActivityCmrViewerBinding

    lateinit var locationManager: LocationManager
    private lateinit var userLocation: Location

    private val list = ArrayList<CoraInfoItem>()
    private var coraList = CoraListDtoListResultDto()

    var dispositivoSeleccionado = -1 //sirve para detectar segunda pulsacion



    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCmrViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1 - Verificar que los permisos estan concedidos
        verificarPermisos()

    }

    // PARA coger la lat y long al presionar en el mapa
    private fun setUpMap() //If the setUpMapIfNeeded(); is needed then...
    {
        mMap.setOnMapClickListener {
            it.latitude
            it.longitude

            createMarker(it.latitude, it.longitude, USERLOCATION, true, true)

            Toast.makeText(this@CmrViewerActivity , "SelectedCora:$it.latitude, $it.longitude, dispositivo seleccionado$dispositivoSeleccionado", Toast.LENGTH_SHORT)
                .show()
        }
        mMap.setOnMapLongClickListener{

        }
    }

    private fun processDownloadAndUI() {
        val tempList = getCoraCloud()
        setUI(list)
        launchMap()

    }
    private fun getCoraCloud():  ArrayList<CoraInfoItem>{
       var tempList =  ArrayList<CoraInfoItem>()
        runBlocking {
           val job: Job = launch(context = Dispatchers.Default) {

                try {
                    val cora = CoraApi(LoginGlobalCredentials.basePathUrl)
                    coraList = cora.apiServicesAppCoraGetCloudCorasGet()

                    Log.e(LOGTAG, ("$coraList"))
                        for (coras in coraList.items!!) {
                            val latitude =  coras.latitude?.toDouble()
                            val longitude =  coras.longitude?.toDouble()
                            var reverseGeocoding = ""
                            if (latitude != null && longitude != null) {
                                reverseGeocoding = getAddressInfo(latitude, longitude)
                            }
                            list.add(CoraInfoItem(coras.description, coras.coraIdStr, reverseGeocoding))
                        }
                    tempList = list
                } catch (e: ServerException) {
                    Log.e(LOGTAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                } catch (e: ClientException) {
                    Log.e(LOGTAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                } catch (e: IOException) {
                    Log.e(LOGTAG, ("[${e.message.toString()}"))
                }
            }
           job.join()
        }
        return  tempList
    }

    private fun getAddressInfo(latitude:Double, longitude:Double): String{
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)

        val address: String = addresses[0].getAddressLine(0)
        val city: String = addresses[0].locality
        val state: String = addresses[0].adminArea
        val country: String = addresses[0].countryName
        val postalCode: String = addresses[0].postalCode
        val knownName: String = addresses[0].featureName

        return "$address\r\n$city"
    }

    private fun setUI(lista: ArrayList<CoraInfoItem>) {
        val adapter = CoraInfoAdapter(lista, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : CoraInfoAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val coraSelected = coraList.items?.get(position)

                Toast.makeText(this@CmrViewerActivity , "SelectedCora:$position dispositivo seleccionado$dispositivoSeleccionado", Toast.LENGTH_SHORT)
                    .show()
                if(dispositivoSeleccionado == position) {
                    Toast.makeText(this@CmrViewerActivity , "SelectedCora:$coraSelected", Toast.LENGTH_SHORT)
                        .show()
                    if (coraSelected != null) {
                        val intent = Intent(this@CmrViewerActivity, CmrBleScanner::class.java)
                        intent.putExtra("CORADTO", coraSelected)
                        startActivity(intent)
                    }
                } else {

                    // Para obligar a cargar la posicion en el caso que sea -1
                    if (dispositivoSeleccionado == -1) {
                        dispositivoSeleccionado = position
                    }
                    //selecciono la celda cogiendo la posicion real del adapter
                    adapter.setCellPosition(position)
                    dispositivoSeleccionado = position

                    //TEST CELL SELECTION
                    // Limpiar la selección de la cell
   //                 adapter.cleanBackground(binding.recyclerView)
                    //adapter.getItem(position).isSelected = true

   //                adapter.setCellBackgroundColor(position, binding.recyclerView,applicationContext.getColor(R.color.colorselectedcell))
   /*                 binding.recyclerView.children.iterator().forEach { item ->
                        item.setBackgroundColor(applicationContext.getColor(R.color.white)
                        )
                    }
                       // binding.recyclerView[position].setBackgroundColor(applicationContext.getColor(R.color.colorselectedcell))
   //                 adapter.setCellBackgroundColor(position, binding.recyclerView,applicationContext.getColor(R.color.colorselectedcell))
                  //  adapter.setCellBackgroundColor(position, binding.recyclerView,  applicationContext.getColor(R.color.colorselectedcell))
*/

                    if (coraSelected != null) {
                        coraSelected.latitude?.let {
                            coraSelected.longitude?.let { it1 ->
                                setZoomToPosition(
                                    it.toDouble(),
                                    it1.toDouble()
                                )
                            }
                        }
                    }
                }

               // val intent = Intent(this@CmrViewerActivity, CmrViewerActivity::class.java)
               // this@CmrViewerActivity.startActivity(intent)

            }
        })
    }

    private fun launchMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    /**
     *
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        // val sydney = LatLng(-34.0, 151.0)
        // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // configurar el TAP manual en mapa
        setUpMap()

        if (coraList.items != null ) {
             for (coras in coraList.items!!) {
                 val latitude = coras.latitude?.toDouble()
                 val longitude = coras.longitude?.toDouble()
                 val nombreCora = coras.description
                 if (latitude != null && longitude != null) {
                     if (nombreCora != null) {
                         createMarker(latitude, longitude, nombreCora, false, false)
                     } else {
                         createMarker(latitude, longitude, "---", false, false)
                     }
                 }
             }
        }
        getLocation()
    }

    private fun createMarker(latitude: Double, longitude: Double, coraName: String, zoom: Boolean, isUserLocation: Boolean) {
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

    }

    private  fun setZoomToPosition(latitude: Double, longitude: Double) {
        val position = LatLng(latitude,longitude)
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(position, 18f),
            1000,
            null
        )
    }

    //Suprime los permisos porque los pido antes
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Si el GPS esta activo
      /*  if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                LocationListener {

                })
        } */
        // MAS RAPIDO
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F
            ) { }

            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (location != null) {
                    userLocation = location
                    createMarker(userLocation.latitude, userLocation.longitude, USERLOCATION, true, false)
                }

        }
    }

    private fun verificarPermisos() {
        if (checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED  ) {
            val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
            requestPermissions(permissions, PERMISSION_REQUEST_ACCESS_LOCATION)
        } else{
            processDownloadAndUI()
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
                    processDownloadAndUI()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}