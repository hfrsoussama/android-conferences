package dev.lesam.androidconferences

import androidx.compose.ui.Modifier
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
                HomeScreen(
                    modifier = Modifier.testTag(homeScreenTestTag)
                )
            }
        }
    }

    @Test
    fun homeScreenShouldHaveFiveComposables() {
        composeAndroidTestRule.apply {

            onRoot().printToLog(tag = "##### LOG-TAG #####")

            onNodeWithTag(homeScreenTestTag)
                .onChildren()
                .assertCountEquals(3) // Divder() Composable is not counted as a Node
        }
    }
}