package com.example.googlelocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.MessageQueue
import android.widget.SearchView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlelocation.databinding.ActivitySuggestBinding
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient


class Suggest : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivitySuggestBinding
    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuggestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intialized the places
        Places.initialize(applicationContext, "AIzaSyDvqRFZNjjXlF0dG3YCgn8dOnOv3a69QGI")
        placesClient = Places.createClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    // Perform place autocomplete here
                    performPlaceAutocomplete(newText)
                }
                return true
            }

        })
    }

    /**
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
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    private fun performPlaceAutocomplete(query: String)
    {
        val token = AutocompleteSessionToken.newInstance()
        val fields = listOf(Place.Field.ID, Place.Field.NAME)

        val request = com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest.builder()
            .setSessionToken(token)
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                // Handle the place autocomplete predictions
                for (prediction in response.autocompletePredictions) {
                    val placeId = prediction.placeId
                    val placeName = prediction.getPrimaryText(null).toString()

                    // Fetch the place details including LatLng
                    val placeFields = listOf(Place.Field.LAT_LNG)
                    val fetchRequest = FetchPlaceRequest.builder(placeId, placeFields).build()

                    placesClient.fetchPlace(fetchRequest)
                        .addOnSuccessListener { fetchResponse: FetchPlaceResponse ->
                            val place = fetchResponse.place
                            val placeLatLng = place.latLng

                            // Add a marker for each suggestion on the map
                            if (placeLatLng != null) {
                                mMap.addMarker(MarkerOptions().position(placeLatLng).title(placeName))
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Handle errors when fetching place details
                            exception.printStackTrace()
                        }
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors during place autocomplete
                exception.printStackTrace()
            }
    }
}