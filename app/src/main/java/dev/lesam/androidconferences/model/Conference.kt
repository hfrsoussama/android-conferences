package dev.lesam.androidconferences.model

data class Presentation(
    val presenter: Presenter,
    val title: String,
    val type: PresentationType = PresentationType.Talk,
    val synopsis: String,
    val startsAt: Long,
    val duration: Int = 30,
    val requiredLevel: Level = Level.Fundamentals,
    val id: Int = 0
)

data class Presenter(
    val name: String,
    val familyName: String,
    val bio: String = ""
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


