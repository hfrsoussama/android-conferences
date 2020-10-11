package dev.lesam.androidconferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.lesam.androidconferences.ui.AndroidConferencesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(ContextAmbient, Modifier.layoutId("homeScreen"))
        }
    }
}

@Composable
fun HomeScreen(ContextAmbient: ProvidableAmbient<Context>, modifier: Modifier) {
    AndroidConferencesTheme {
        Column(modifier = modifier) {
            val context = ContextAmbient.current
            val openOssActivityOnClick = {
                context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
            }
            Greeting("Alfred Sisley")
            Greeting("3 minutes ago")
            Button(onClick = openOssActivityOnClick) {
                Text(text = "See Licences")
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

@Preview(group = "Screens", showBackground = true, heightDp = 300, widthDp = 300)
@Composable
fun HomeScreenPreview() {
    val context = ContextAmbient.current
    HomeScreen(ContextAmbient = ContextAmbient, modifier = Modifier.None)
}