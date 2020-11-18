package dev.lesam.androidconferences

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle
import dev.lesam.androidconferences.ui.AndroidConferencesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenActivityTest {

    @get: Rule
    val composeAndroidTestRule = createAndroidComposeRule<HomeScreenActivity>()

    private val homeScreenTestTag = "HomeScreen"

    @Before
    fun setup() {
        composeAndroidTestRule.setContent {
            AndroidConferencesTheme {
                HomeScreenWithViewModel(
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