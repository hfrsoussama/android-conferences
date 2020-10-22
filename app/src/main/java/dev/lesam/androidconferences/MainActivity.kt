package dev.lesam.androidconferences

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.oss.licenses.OssLicensesActivity
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
fun HomeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val count: Int by viewModel.counter.observeAsState(0)
        Text(
            text = "I am the Composer n#${count}",
            modifier = Modifier.padding(all = 16.dp)
        )
        Divider(color = Color.LightGray, thickness = 8.dp)
        LicencesButton()
        Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
        PresenterCard()
        Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
        OutlinedButton(onClick = { viewModel.incrementCounter(1) }, modifier = Modifier.padding(all = 12.dp)) {
            Text(text = " + 1 ", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun LicencesButton() {
    val context = ContextAmbient.current
    val onClickOpenOssActivity = {
        context.startActivity(
            Intent(
                context,
                OssLicensesActivity::class.java
            )
        )
    }
    Button(onClick = onClickOpenOssActivity) {
        Text(text = "See Licences")
    }
}

@Preview(
    group = "Single component"
)
@Composable
fun LicencesButtonPreview() {
    AndroidConferencesTheme {
        LicencesButton()
    }
}

@Preview(group = "Themed Screens", showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun HomeScreenThemedPreview() {
    AppTheme {
        HomeScreen()
    }
}

@Preview(
    group = "Unthemed Screens",
    showBackground = true,
    device = Devices.NEXUS_5X)
@Composable
fun HomeScreenUnthemedPreview() {
    AppTheme {
        HomeScreen(modifier = Modifier.background(Color.Cyan))
    }
}

@Preview(
    group = "Single component"
)
@Composable
fun ConferenceListItem(conference: Conference = Conference()) {
    AndroidConferencesTheme {
        Card(elevation = 4.dp, modifier = Modifier.width(400.dp).height(92.dp)) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {
                Text(conference.name, style = MaterialTheme.typography.h4)
                Text(conference.cityName, style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}

data class Conference(
    val name: String = "Droidcon",
    val cityName: String = "Berlin"
)

@Preview(
    group = "Single component",
    widthDp = 400
)
@Composable
fun PresenterCardPreview() {
    Surface(color = MaterialTheme.colors.surface) {
        PresenterCard()
    }
}

@Composable
fun PresenterCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* Do Nothing */ })
            .padding(all = 8.dp)
    ) {
        Surface(
            modifier = Modifier.preferredSize(62.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Text(text = "Oussama Hafferssas", fontWeight = FontWeight.Bold)
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    text = "Interesting Facts About Compose - Alpha Edition",
                    style = MaterialTheme.typography.body2
                )
            }
        }

    }
}