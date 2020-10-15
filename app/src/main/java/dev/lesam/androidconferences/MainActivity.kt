package dev.lesam.androidconferences

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.lesam.androidconferences.ui.AndroidConferencesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                modifier = Modifier.layoutId("homeScreen")
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {
    AndroidConferencesTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                val context = ContextAmbient.current
                val openOssActivityOnClick = {
                    context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                }
                Text(
                    text = "I am a Composer",
                    modifier = Modifier.padding(all = 16.dp)
                )
                Button(onClick = openOssActivityOnClick) {
                    Text(text = "See Licences")
                }
            }
        }
    }
}

@Preview(group = "Screens", showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier.background(Color.Cyan))
}