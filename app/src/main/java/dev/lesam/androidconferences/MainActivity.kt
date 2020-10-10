package dev.lesam.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import dev.lesam.androidconferences.ui.AndroidConferencesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConferencesTheme {
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
                Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                    title = { Text(text = "Android Conferences") },
                                    backgroundColor = MaterialTheme.colors.primary
                            )
                        },
                        bodyContent = {
                            Surface(color = MaterialTheme.colors.background) {
                                Greeting("Android")
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidConferencesTheme {
        Greeting("Android")
    }
}