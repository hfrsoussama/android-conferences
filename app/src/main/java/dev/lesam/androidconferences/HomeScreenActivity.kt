package dev.lesam.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
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

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreenWithViewModel(
                    modifier = Modifier.layoutId("homeScreen")
                )
            }
        }
    }
}

@Composable
fun HomeScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val presentations by viewModel.listOfPresentations.observeAsState()
    val onPresentationsListItemClick = { presentation: Presentation -> viewModel.printItem(presentation) }
    val onAddPresentationClick = { viewModel.addNewPresentation() }

    HomeScreen(
        modifier = modifier,
        presentations = presentations.orEmpty(),
        onPresentationsListItemClick = onPresentationsListItemClick,
        onAddPresentationClick = onAddPresentationClick
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    presentations: List<Presentation>,
    onPresentationsListItemClick: (Presentation) -> Unit = {},
    onAddPresentationClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Android Conferences", maxLines = 1)
                },
                actions = {
                    IconButton(onClick = onAddPresentationClick) {
                        Icon(imageVector = Icons.Sharp.PlusOne)
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
        contentPadding = PaddingValues(all = 8.dp),
        items = presentations,
    ) { item ->
        PresentationResumeCard(
            presentation = item,
            onPresentationClick = onPresentationClick
        )
    }
}



