package dev.lesam.androidconferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.lesam.androidconferences.model.Level
import dev.lesam.androidconferences.model.Performance
import dev.lesam.androidconferences.model.Presenter
import java.util.*

class MainViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    private val _listOfPerformances = MutableLiveData(
        getListOfPerformances()
    )
    val listOfPerformances: LiveData<List<Performance>> = _listOfPerformances



    fun incrementCounter(incrementBy: Int) {
        _counter.value = _counter.value?.plus(incrementBy)
    }


}

fun getListOfPerformances(): List<Performance> {
    val performances = mutableListOf<Performance>()
    val performance = Performance(
        presenter = Presenter(
            name = "Oussama",
            familyName = "Hafferssas",
            bio = "Software Engineer"
        ),
        title = "Intersting facts about Jetpack Compose",
        synopsis = "Even though Jetpack Compose just hit alpha, it has generated huge interest in the Android community. This workshop covers an introduction to the fundamental parts of Compose: setting up views, managing state, and theming. Combining these three areas provides a delightful user experience for any app. By the end of the session, viewers will have an understanding of how to create a UI in compose and have confidence in their ability to use Compose to quickly create a screen in their apps.",
        startsAt = Calendar.getInstance(TimeZone.getTimeZone("GMT+1")).apply {
            set(2020, 11, 14, 20, 0)
        }.timeInMillis,
        duration = 60,
        requiredLevel = Level.Intermediate
    )

    repeat(10) {
        performances.add(performance.copy())
    }
    return performances.toList()
}
