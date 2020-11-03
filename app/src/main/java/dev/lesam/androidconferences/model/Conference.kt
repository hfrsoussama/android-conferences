package dev.lesam.androidconferences.model

data class Presenter(
    val name: String,
    val familyName: String,
    val bio: String = ""
)

data class Performance(
    val presenter: Presenter,
    val title: String,
    val type: PerformanceType = PerformanceType.Talk,
    val synopsis: String,
    val startsAt: Long,
    val duration: Int = 30,
    val requiredLevel: Level = Level.Fundamentals
)

sealed class PerformanceType {
    object Talk : PerformanceType()
    object Workshop : PerformanceType()
}

sealed class Level {
    object Fundamentals : Level()
    object Intermediate : Level()
    object Advanced : Level()
}


