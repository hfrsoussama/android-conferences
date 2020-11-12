package dev.lesam.androidconferences.slides.helloworld.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.lesam.androidconferences.slides.helloworld.compose.ui.AndroidConferencesTheme

class HelloWorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConferencesTheme {
                Greeting(name = "World")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        modifier = Modifier.padding(all = 32.dp),
        text = "Hello $name!"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidConferencesTheme {
        Greeting("World")
    }
}