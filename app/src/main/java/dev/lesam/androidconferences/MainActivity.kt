package dev.lesam.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import dev.lesam.androidconferences.ui.AndroidConferencesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen(
                    modifier = Modifier.layoutId("homeScreen")
                )
            }
        }
    }
}

@Composable
fun AppTheme(composableComponent: @Composable () -> Unit) {
    AndroidConferencesTheme {
        Surface(color = MaterialTheme.colors.background) {
            composableComponent()
        }
    }
}

// 1 - stateless
// 2 - stateful
// 3 - stateful VM
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val counterViewModel: CounterViewModel = viewModel()
        val counter = counterViewModel.counter.observeAsState()
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = "I am the Composer n#${counter.value}"
        )
        Divider(color = Color.LightGray, thickness = 8.dp)
        OutlinedButton(onClick = { counterViewModel.incrementCounter() }) {
            Text(text = " + 1 ")
        }
    }
}

@Preview(group = "Themed Screens", showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun HomeScreenThemedPreview() {
    AppTheme {
        HomeScreen()
    }
}

@Preview(group = "Unthemed Screens", showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun HomeScreenUnthemedPreview() {
    AppTheme {
        HomeScreen(modifier = Modifier.background(Color.Cyan))
    }
}