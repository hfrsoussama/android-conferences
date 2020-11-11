package dev.lesam.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.sharp.AddAlert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.lesam.androidconferences.model.Presentation
import dev.lesam.androidconferences.ui.AppTheme

class PresentationListProvider: PreviewParameterProvider<List<Presentation>> {
    override val values: Sequence<List<Presentation>>
        get() = listOf(getListOfPresentations().take(5)).asSequence()

}


@Preview(group = "Themed Screens", showBackground = true, heightDp = 800, widthDp = 400)
@Composable
fun HomeScreenThemedPreview(
    @PreviewParameter(PresentationListProvider::class) presentations: List<Presentation>
) {
    AppTheme {
        HomeScreen(presentations = presentations)
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreenViewModelComposable(
                    modifier = Modifier.layoutId("homeScreen")
                )
            }
        }
    }
}

@Composable
fun HomeScreenViewModelComposable(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val presentations by viewModel.listOfPresentations.observeAsState()
    val onPresentationsListItemClick = { presentation: Presentation -> viewModel.printItem(presentation) }

    HomeScreen(
        modifier = modifier,
        presentations = presentations.orEmpty(),
        onPresentationsListItemClick = onPresentationsListItemClick
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    presentations: List<Presentation>,
    onPresentationsListItemClick: (Presentation) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Android Conferences", maxLines = 1)
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Filled.Info)
                    }
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Sharp.AddAlert)
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeScreenBody(
            modifier = modifier.padding(innerPadding),
            presentations = presentations,
            onPresentationClick = onPresentationsListItemClick
        )
    }

}

@Composable
fun HomeScreenBody(
    modifier: Modifier = Modifier,
    presentations: List<Presentation>,
    onPresentationClick: (Presentation) -> Unit = {}
) {
    LazyColumnFor(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        items = presentations,
    ) { item ->
        PresentationResumeCard(
            presentation = item,
            onPresentationClick = onPresentationClick
        )
    }
}



