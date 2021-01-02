package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestBinService {
    @POST("/")
    fun postWifiNames(@Body wifiScanResult: List<String>): Call<Void>
}