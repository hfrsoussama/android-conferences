package dev.lesam.androidconferences.model

data class Presenter(
    val name: String,
    val familyName: String,
    val bio: String = ""
)

data class Presentation(
    val presenter: Presenter,
    val title: String,
    val type: PresentationType = PresentationType.Talk,
    val synopsis: String,
    val startsAt: Long,
    val duration: Int = 30,
    val requiredLevel: Level = Level.Fundamentals
)

sealed class PresentationType {
    object Talk : PresentationType()
    object Workshop : PresentationType()
}

sealed class Level {
    object Fundamentals : Level()
    object Intermediate : Level()
    object Advanced : Level()
}

