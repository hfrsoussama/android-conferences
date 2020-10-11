package dev.lesam.androidconferences

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import dev.lesam.androidconferences.ui.AndroidConferencesTheme
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidConferencesTheme {
                Column {
                    val context = ContextAmbient.current
                    val openOssActivityOnClick = {
                        startActivity(
                            Intent(
                                context,
                                OssLicensesMenuActivity::class.java
                            )
                        )
                    }
                    Text("Alfred Sisley")
                    Text("3 minutes ago")
                    Button(onClick = openOssActivityOnClick) {
                        Text(text = "Click me !")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, group = "Texts")
@Composable
fun PreviewGreeting() {
    AndroidConferencesTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, group = "Texts")
@Composable
fun DefaultPreview() {
    Column {
        Greeting("Android")
        Greeting("Koko")
        Button(onClick = { Log.d("KOO", "koko") }) {
            Text(text = "Click me !")
        }
    }
}