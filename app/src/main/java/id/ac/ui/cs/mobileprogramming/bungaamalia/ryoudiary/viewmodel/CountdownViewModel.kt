package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountdownViewModel : ViewModel() {
    var currentValue = MutableLiveData<Int>()
    var mainValue = MutableLiveData<String>()
    var secondsText = MutableLiveData<String>()

    init {
        Log.i("CountDownViewModel", "CountDownViewModel created!")
        currentValue.value = 0
        mainValue.value = "0"
        secondsText.value = "R"
    }

    fun plusValue(): String {
        currentValue.value = (currentValue.value)?.plus(1)
        return currentValue.value.toString()
    }

    fun minusValue(): String {
        currentValue.value = (currentValue.value)?.minus(1)
        return currentValue.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("CountdownViewModel", "CountdownViewModel destroyed!")
    }
}