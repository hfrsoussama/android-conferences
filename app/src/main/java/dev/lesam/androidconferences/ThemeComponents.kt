package dev.lesam.androidconferences

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.lesam.androidconferences.model.Performance


class PerformanceProvider: PreviewParameterProvider<Performance> {
    override val values: Sequence<Performance>
        get() = sequenceOf(
            getListOfPerformances().first()
        )
}


class ThemesProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}

@Composable
fun AppThemedPreview(
    @PreviewParameter(ThemesProvider::class) isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    AppTheme(isDarkTheme) {
        content()
    }
}


@Preview(group = "Single component", widthDp = 400)
@Composable
fun PresenterCardPreview2(
    @PreviewParameter(ThemesProvider::class) isDarkTheme: Boolean,
) {
    AppThemedPreview(isDarkTheme = isDarkTheme) {
        PerformanceResumeCard(performance = getListOfPerformances().first())
    }
}




@Composable
fun PerformanceResumeCard(
    modifier: Modifier = Modifier,
    performance: Performance
) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(all = 8.dp)
    ) {
        Surface(
            modifier = Modifier.preferredSize(62.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) { }
        Column(
            modifier = modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Text(text = "${performance.presenter.name} ${performance.presenter.familyName}", fontWeight = FontWeight.Bold)
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    text = "Interesting Facts About Compose - Alpha Edition",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
