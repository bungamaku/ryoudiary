package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentHomeBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.service.RequestBinService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val PERMISSION_REQUEST_LOCATION = 0

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

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
        binding.homeListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipeListFragment)
        }
        binding.homeCountdownButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_InputCountdownFragment)
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