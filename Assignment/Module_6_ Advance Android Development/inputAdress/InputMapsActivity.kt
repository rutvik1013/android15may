package com.example.maps

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.maps.databinding.ActivityInputMapsBinding

class InputMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityInputMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.showLocationButton.setOnClickListener {
            showmarker()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        mMap=map
    }
    private fun showmarker()
    {
        var adress=binding.addressEditText.text.toString()

        if (adress.isEmpty())
        {
            Toast.makeText(applicationContext, "You have to enter adress", Toast.LENGTH_SHORT).show()
            return
        }
        val geocoder=Geocoder(this)
        var Adress:List<Address> =geocoder.getFromLocationName(adress,1)!!

        if (Adress.isNotEmpty())
        {
            var location=LatLng(Adress[0].latitude,Adress[0].longitude)
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(location).title(adress))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f))

        }
        else
        {
            Toast.makeText(applicationContext, "Not Found Adress", Toast.LENGTH_SHORT).show()
        }
    }

}