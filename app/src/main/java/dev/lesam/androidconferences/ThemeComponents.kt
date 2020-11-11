package dev.lesam.androidconferences

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.lesam.androidconferences.model.Presentation
import dev.lesam.androidconferences.ui.AppThemedPreview
import dev.lesam.androidconferences.ui.ThemesProvider


class PresentationProvider: PreviewParameterProvider<Presentation> {
    override val values: Sequence<Presentation>
        get() = sequenceOf(
            getListOfPresentations().first()
        )
}



@Preview(group = "Single component", widthDp = 400)
@Composable
fun PresenterCardPreview2(
    @PreviewParameter(ThemesProvider::class) isDarkTheme: Boolean,
) {
    AppThemedPreview(isDarkTheme = isDarkTheme) {
        PresentationResumeCard(presentation = getListOfPresentations().first())
    }
}




@Composable
fun PresentationResumeCard(
    modifier: Modifier = Modifier,
    presentation: Presentation,
    onPresentationClick: (Presentation) -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(all = 8.dp)
            .clickable(onClick = { onPresentationClick(presentation) })
    ) {
        Surface(
            modifier = Modifier.preferredSize(62.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) { }
        Column(
            modifier = modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "${presentation.presenter.name} ${presentation.presenter.familyName}",
                fontWeight = FontWeight.Bold
            )
            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                Text(
                    text = presentation.title,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
