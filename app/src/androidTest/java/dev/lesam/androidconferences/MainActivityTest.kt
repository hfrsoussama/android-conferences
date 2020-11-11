package dev.lesam.androidconferences

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.Lifecycle
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
                HomeScreenViewModelComposable(
                    modifier = Modifier.testTag(homeScreenTestTag)
                )
            }
        }
    }

    @Test
    fun homeScreenShouldHaveFourComposables() {
        composeAndroidTestRule.apply {

            onRoot().printToLog(tag = "##### LOG-TAG #####")

            onNodeWithTag(homeScreenTestTag)
                .onChildren()
                .assertCountEquals(4) // Divder() Composable is not counted as a Node
        }
    }

    @Test
    fun buttonToSeeLicencesMustHaveAction() {
        composeAndroidTestRule.apply {
            this.activityRule.scenario.moveToState(Lifecycle.State.RESUMED) // Activity must be in the resume state
            onNodeWithText("See Licences")
                .assertHasClickAction()
        }
    }

}