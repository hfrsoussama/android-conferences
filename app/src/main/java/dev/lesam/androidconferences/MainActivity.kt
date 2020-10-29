package dev.lesam.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.sharp.AddAlert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Android Conferences", maxLines = 1)
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Filled.Info)
                    }
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Sharp.AddAlert)
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeScreenBody(modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel: MainViewModel = viewModel()
        val count: Int by viewModel.counter.observeAsState(0)
        Text(
            text = "I am the Composer n#${count}",
            modifier = Modifier.padding(all = 16.dp)
        )
        PresenterCard()
        OutlinedButton(
            onClick = { viewModel.incrementCounter(1) },
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Text(text = " + 1 ", style = MaterialTheme.typography.h4)
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




