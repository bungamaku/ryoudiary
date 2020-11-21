package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class CountdownViewModel : ViewModel() {
    var currentValue: Int = 0
    var mainValue: String = "0"
    var secondsText: String = "R"

    init {
        Log.i("CountDownViewModel", "CountDownViewModel created!")
    }

    fun plusValue(): String {
        currentValue++
        return currentValue.toString()
    }

    fun minusValue(): String {
        currentValue--
        return currentValue.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CountdownViewModel", "CountdownViewModel destroyed!")
    }
}