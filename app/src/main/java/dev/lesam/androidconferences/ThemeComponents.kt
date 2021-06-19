package dev.lesam.androidconferences

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.lesam.androidconferences.model.Presentation
import dev.lesam.androidconferences.ui.AppThemedPreview
import dev.lesam.androidconferences.ui.ThemesProvider


class PresentationProvider : PreviewParameterProvider<Presentation> {
    override val values: Sequence<Presentation>
        get() = sequenceOf(
            getListOfPresentations().first()
        )
}


@ExperimentalAnimationApi
@Preview(group = "Single component", widthDp = 400)
@Composable
fun PresentationResumeCardPreview(
    @PreviewParameter(ThemesProvider::class) isDarkTheme: Boolean,
) {
    AppThemedPreview(isDarkTheme = isDarkTheme) {
        PresentationResumeCard(presentation = getListOfPresentations().first())
    }
}


@ExperimentalAnimationApi
@Composable
fun PresentationResumeCard(
    modifier: Modifier = Modifier,
    presentation: Presentation,
    onPresentationClick: (Presentation) -> Unit = {}
) {

    var expanded by remember { mutableStateOf(false)}

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(all = 8.dp)
            .clickable(onClick = {
                onPresentationClick(presentation)
                expanded = !expanded
            }),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(all = 8.dp)
        ) {
            Row {
                Surface(
                    modifier = Modifier.size(62.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.jetpack_compose_icon_128_rbg),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "${presentation.presenter.name} ${presentation.presenter.familyName}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "#${presentation.id} ${presentation.title}",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                )
            }
        }
    }

}
