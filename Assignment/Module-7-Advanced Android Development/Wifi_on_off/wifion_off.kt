package com.example.musicassign

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.musicassign.databinding.ActivityWifionOffBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class wifion_off : AppCompatActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var binding: ActivityWifionOffBinding
    private lateinit var wifi: WifiManager

    private val wifiManager:WifiManager by lazy {
        applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWifionOffBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission())

        {
            isGranted:Boolean->
            if (isGranted)
            {
                starthotspot()
            }
            else
            {
                Toast.makeText(applicationContext, "Please alllow nearby device permission", Toast.LENGTH_SHORT).show()
            }
        }
        binding.wifionbtn.setOnClickListener {
            checkSelfPermissions()
        }
        binding.wifioffbtn.setOnClickListener {
            wifi.setWifiEnabled(false)
        }

    }
    @SuppressLint("MissingPermission", "NewApi")
    private fun starthotspot()
    {
       wifiManager.startLocalOnlyHotspot(object :WifiManager.LocalOnlyHotspotCallback() {
           override fun onStarted(reservation: WifiManager.LocalOnlyHotspotReservation?) {
               super.onStarted(reservation)
               binding.wifionbtn.isEnabled = false
               Toast.makeText(
                   applicationContext,
                   "Status Local Only hotspot:Started",
                   Toast.LENGTH_SHORT
               ).show()
           }

           override fun onFailed(reason: Int) {
               super.onFailed(reason)
               Toast.makeText(applicationContext, "Local Only hotspot:Stopped", Toast.LENGTH_SHORT)
                   .show()

           }


       },null,
       )


    }

    private fun checkSelfPermissions()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            var permission:String=android.Manifest.permission.NEARBY_WIFI_DEVICES
            when
            {
                ContextCompat.checkSelfPermission(this,permission,)==PackageManager.PERMISSION_GRANTED->
                {
                    starthotspot()
                }
                shouldShowRequestPermissionRationale(permission)->{
                    MaterialAlertDialogBuilder(this).setMessage("This app would not work without NEARBY wifi devices. Do you want to give app permission?")
                        .setPositiveButton("Yes"){_,_->
                            permissionLauncher.launch(permission)
                        }.setNegativeButton("No"){_,_->

                        }.show()
                }
                else->
                {
                    permissionLauncher.launch(permission)
                }
            }

        }
        else
        {
            Toast.makeText(this, "Please use android 13 devices", Toast.LENGTH_SHORT).show()
        }
    }
}
