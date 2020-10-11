package dev.lesam.androidconferences

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.testTag
import androidx.ui.test.*
import dev.lesam.androidconferences.ui.AndroidConferencesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    private val homeScreenTestTag = "HomeScreen"

    @Before
    fun setup() {
        composeAndroidTestRule.setContent {
            AndroidConferencesTheme {
                val contextAmbient = ContextAmbient
                HomeScreen(
                    ContextAmbient = contextAmbient,
                    modifier = Modifier.testTag(homeScreenTestTag)
                )
            }
        }
    }

    @Test
    fun homeScreenShouldHaveThreeComposables() {
        composeAndroidTestRule.apply {

            onRoot().printToLog("######")

            onNodeWithTag(homeScreenTestTag)
                .onChildren()
                .assertCountEquals(3)
        }
    }
}