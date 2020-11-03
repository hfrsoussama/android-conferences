package dev.lesam.androidconferences

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import kotlin.random.Random

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

data class Presenter(val name: String, val familyName: String)

@Composable
fun PresenterCard(
    modifier: Modifier = Modifier,
    presenter: Presenter = Presenter("Ousssama", "Hafferssas")
) {
    val color  = MaterialTheme.colors.onSurface
        .copy(alpha = 0.2f, red = Random.nextInt(0, 100).div(100f))
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = color)
            .padding(all = 8.dp)
    ) {
        Surface(
            modifier = Modifier.preferredSize(62.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f, red = Random(255).nextFloat())
        ) { }
        Column(
            modifier = modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Text(text = "${presenter.name} ${presenter.familyName}", fontWeight = FontWeight.Bold)
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    text = "Interesting Facts About Compose - Alpha Edition",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}