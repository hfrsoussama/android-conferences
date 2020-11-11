package dev.lesam.androidconferences

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
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
fun PresentationResumeCardPreview(
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
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(all = 8.dp)
            .clickable(onClick = { onPresentationClick(presentation) }),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(all = 8.dp)
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
                Providers(AmbientContentAlpha provides ContentAlpha.medium, children = {
                    Text(
                        text = presentation.title,
                        style = MaterialTheme.typography.body2
                    )
                })
            }
        }
    }

}
