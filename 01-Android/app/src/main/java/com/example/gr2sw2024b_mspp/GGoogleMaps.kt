package com.example.gr2sw2024b_mspp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar

class GGoogleMaps : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false
    val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
    val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ggoogle_maps)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        solicitarPermisos()
        //Inicializar lógica del mapa
        inicializarLogicaMapa()

        val btn_Carolina = findViewById<Button>(R.id.btn_ir_carolina)
        btn_Carolina.setOnClickListener {
            val carolina = LatLng(-0.1838874666425376, -78.48462687495609)
            moverCamaraConZoom(carolina)
        }

    }

    fun tengoPermisos(): Boolean {
        val contexto = applicationContext
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED
            permisos = tienePermisos
        return permisos
    }

    fun solicitarPermisos() {
        if(!tengoPermisos()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(nombrePermisoFine, nombrePermisoCoarse), 1
            )
        }
    }

    fun inicializarLogicaMapa () {
        val fragmentoMapa = supportFragmentManager.findFragmentById(
            R.id.map
        ) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
                moverQuicentro()
                anadirPoliLinea()
                anadirPoligono()
                escucharListeners()
            }
        }
    }

    fun moverQuicentro() {
        val quicentro = LatLng(-0.17604982296243202, -78.47915825407831)
        val titulo = "Quicentro Norte"
        val marcadorQuicentro = anadirMarcador(quicentro, titulo)
        marcadorQuicentro.tag = titulo
        moverCamaraConZoom(quicentro)
    }

    fun anadirPoliLinea (){
        with(mapa) {
            val polilinea = mapa.addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.17604982296243202, -78.47915825407831),
                        LatLng(-0.21205698072778867, -78.49033537723614),
                        LatLng(-0.2521180963744332, -78.52284538887682)
                    )
            )
            polilinea.tag = "polilinea - 1"
        }
    }

    fun anadirPoligono () {
        with(mapa) {
            val poligono = mapa.addPolygon(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.23337524391110015, -78.49576412171903),
                        LatLng(-0.23327868518910438, -78.49367199871227),
                        LatLng(-0.23551026437207337, -78.49395094844651)
                    )
            )
            poligono.tag = "poligono - 1"

        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            mostrarSnackbar("setOnPolygonClickListener ${it.tag}")
        }
        mapa.setOnPolylineClickListener {
            mostrarSnackbar("setOnPolylineClickListener ${it.tag}")
        }
        mapa.setOnMarkerClickListener {
            mostrarSnackbar("setOnMarkerClickListener ${it.tag}")
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraIdleListener { mostrarSnackbar("setOnCameraIdleListener") }
        mapa.setOnCameraMoveListener { mostrarSnackbar("setOnCameraMoveListener") }
        mapa.setOnCameraMoveStartedListener { mostrarSnackbar("setOnCameraMoveStartedListener") }
    }

    @SuppressLint("MissingPermission")
    fun establecerConfiguracionMapa () {
        with(mapa) {
            if (tengoPermisos()) {
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun moverCamaraConZoom(latLang: LatLng, zoom: Float = 17f) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, zoom))
    }

    fun anadirMarcador(latLang: LatLng, title: String):Marker {
        return mapa.addMarker(MarkerOptions().position(latLang).title(title))!!
    }

    fun mostrarSnackbar(texto: String) {
        var snack = Snackbar.make(
            findViewById(R.id.main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

}