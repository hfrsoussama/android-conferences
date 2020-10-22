package dev.lesam.androidconferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter


    fun incrementCounter(incrementBy: Int) {
        _counter.value = _counter.value?.plus(incrementBy)
    }

}