package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary

import android.util.Log
import androidx.lifecycle.ViewModel

class CountdownViewModel : ViewModel() {
    var currentValue: Int = 0
    var mainValue: String = "0"
    var titleText: String = "Countdown in Sec"
    var secondsText: String = "R"
    var titleSecondText: String = "Counting down from %d"

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