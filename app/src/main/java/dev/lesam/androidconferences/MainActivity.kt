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
import dev.lesam.androidconferences.model.Performance
import dev.lesam.androidconferences.ui.AppTheme

class PerformancesListProvider: PreviewParameterProvider<List<Performance>> {
    override val values: Sequence<List<Performance>>
        get() = listOf(getListOfPerformances().take(5)).asSequence()

}


@Preview(group = "Themed Screens", showBackground = true, heightDp = 600, widthDp = 300)
@Composable
fun HomeScreenThemedPreview(
    @PreviewParameter(PerformancesListProvider::class) performances: List<Performance>
) {
    AppTheme {
        HomeScreenBody(performances = performances)
    }
}

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
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    val performances by viewModel.listOfPerformances.observeAsState()
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
            onClick = { performance -> viewModel.printItem(performance) },
            performances = performances.orEmpty()
        )
    }
}

@Composable
fun HomeScreenBody(
    modifier: Modifier = Modifier,
    performances: List<Performance>,
    onClick: (Performance) -> Unit = {}
) {
    LazyColumnFor(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        items = performances,
    ) { item ->
        PerformanceResumeCard(
            performance = item,
            onClick = onClick
        )
    }
}



