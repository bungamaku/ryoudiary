package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentHomeBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.service.RequestBinService
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var accessFineLocationGranted = false
    private var accessCoarseLocationGranted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeAnimationButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_GLActivity2)
        }
        binding.homeListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeListFragment)
        }
        binding.homeCountdownButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_InputCountdownFragment)
        }

        binding.homeInternetButton.setOnClickListener {
            if (checkInternet()) {
                Toast.makeText(activity?.applicationContext,
                    "Internet connection is available", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(activity?.applicationContext,
                    "Internet connection is not available", Toast.LENGTH_SHORT).show()
            }
        }

        val wifiManager = activity?.applicationContext?.getSystemService(
            Context.WIFI_SERVICE) as WifiManager

        val wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val wifiNames = wifiManager.scanResults.map {
                    it.SSID
                }

                if (wifiNames.isNotEmpty()) {
                    view.findViewById<TextView>(R.id.wifi_textView).text =
                        wifiNames.joinToString(
                            prefix = "Wi-Fi names: [", postfix = "]", separator = ", ")
                }

                val requestBinService = Retrofit.Builder()
                    .baseUrl("https://d71d79b591d245fdd6ef206f7326ea99.m.pipedream.net")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RequestBinService::class.java)

                requestBinService.postWifiNames(wifiNames).enqueue(
                    object: Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable?) {
                            Toast.makeText(activity?.applicationContext,
                                "Sending Wi-Fi names failed with error: ".plus(t?.message),
                                Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Toast.makeText(activity?.applicationContext,
                                "Wi-Fi names sent to RequestBin!",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        activity?.applicationContext?.registerReceiver(wifiScanReceiver, intentFilter)

        binding.homeWifiButton.setOnClickListener {
            handlePermission(
                AppPermission.ACCESS_FINE_LOCATION,
                onGranted = {
                    accessFineLocationGranted = true
                },
                onDenied = {
                    requestPermission(AppPermission.ACCESS_FINE_LOCATION)
                },
                onRationaleNeeded = {
                    requestPermission(AppPermission.ACCESS_FINE_LOCATION)
                }
            )
            handlePermission(
                AppPermission.ACCESS_COARSE_LOCATION,
                onGranted = {
                    accessCoarseLocationGranted = true
                },
                onDenied = {
                    requestPermission(AppPermission.ACCESS_COARSE_LOCATION)
                },
                onRationaleNeeded = {
                    requestPermission(AppPermission.ACCESS_COARSE_LOCATION)
                }
            )
            if (accessFineLocationGranted && accessCoarseLocationGranted) {
                val success = wifiManager.startScan()
                if (success) {
                    Toast.makeText(activity?.applicationContext,
                        "Scanning for Wi-Fi, loading...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity?.applicationContext,
                        "Wi-Fi scanning failed, please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        handlePermissionsResult(requestCode, permissions, grantResults,
            onPermissionGranted = {
                Toast.makeText(activity?.applicationContext,
                    "Permission granted!", Toast.LENGTH_SHORT).show()
            },
            onPermissionDenied = {
                Toast.makeText(activity?.applicationContext,
                    "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun checkInternet(): Boolean {
        val cm = context?.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}