package com.example.trivial.feature.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.ui.R as uiResources
import com.example.trivial.ui.components.TrivialButton
import com.example.trivial.ui.components.TrivialCounter
import com.example.trivial.ui.components.TrivialOptionsSelector
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun QuizRoute(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = koinViewModel(),
    onStartQuizClick: () -> Unit
) {
    QuizSetupScreen(
        modifier = modifier,
        onStartQuizClick = onStartQuizClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuizSetupScreen(
    modifier: Modifier = Modifier,
    onStartQuizClick: () -> Unit,
) {
    var difficulty by remember { mutableStateOf(TriviaDifficulty.Medium) }
    var category by remember { mutableIntStateOf(-1) }
    var type by remember { mutableStateOf(TriviaQuestionType.MultipleChoice) }
    var amount by remember { mutableIntStateOf(10) }
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(TrivialSize.SizeMedium)
    ) {
        Text(
            text = "Quiz Setup",
            style = MaterialTheme.typography.headlineLarge,
            color = TrivialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(TrivialSize.SizeLarge))
        Text(
            text = "Difficulty",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        TrivialOptionsSelector(
            modifier = Modifier.padding(vertical = TrivialSize.SizeMedium),
            selectedOption = "Medium",
            options = listOf("Easy", "Medium", "Hard")
        ) { }
        Text(
            text = "Category",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        Row(
            modifier = Modifier
                .padding(vertical = TrivialSize.SizeMedium)
                .background(TrivialTheme.colors.neutralWhite)
                .clickable { openBottomSheet = true },
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(TrivialSize.SizeMedium),
                text = if (category == -1) "Select category" else Categories.list.first { it.id == category }.name,
                style = MaterialTheme.typography.labelLarge,
                color = TrivialTheme.colors.neutralBlack
            )
            Spacer(modifier = Modifier.width(TrivialSize.SizeMedium))
            Icon(
                modifier = Modifier.padding(TrivialSize.SizeMedium),
                painter = painterResource(if (category == -1) uiResources.drawable.chevron_right else uiResources.drawable.edit),
                tint = TrivialTheme.colors.neutralBlack,
                contentDescription = null,
            )
        }
        Text(
            text = "Type",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        TrivialOptionsSelector(
            modifier = Modifier.padding(vertical = TrivialSize.SizeMedium),
            selectedOption = "Multiple Choice",
            options = listOf("Multiple Choice", "True / False")
        ) { }
        Text(
            text = "Number of questions",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        TrivialCounter(modifier = Modifier.padding(vertical = TrivialSize.SizeMedium), count = amount, min = 2) {
            amount = it
        }
        Spacer(modifier = Modifier.weight(1f))
        TrivialButton(text = "PLAY", containerColor = TrivialTheme.colors.secondary) { }
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            containerColor = TrivialTheme.colors.neutralWhite,
            onDismissRequest = { openBottomSheet = false },
        ) {
            CategoryBottomSheetContent(
                selectedCategory = category,
                onCategorySelected = {
                    category = it
                },
                onDismiss = {
                    openBottomSheet = false
                }
            )
        }
    }
}

@Composable
private fun CategoryBottomSheetContent(
    modifier: Modifier = Modifier,
    selectedCategory: Int = -1,
    onCategorySelected: (id: Int) -> Unit,
    onDismiss: () -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(TrivialSize.SizeSmall),
        verticalArrangement = Arrangement.spacedBy(TrivialSize.SizeSmall),
        contentPadding = PaddingValues(TrivialSize.SizeMedium)
    ) {
        items(Categories.list) { category ->
            FilterChip(
                selected = category.id == selectedCategory,
                label = { Text(category.name, style = MaterialTheme.typography.labelLarge) },
                leadingIcon = category.icon?.let { icon ->
                    { Text(icon) }
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = TrivialTheme.colors.neutralWhite,
                    labelColor = TrivialTheme.colors.neutralBlack,
                    selectedContainerColor = TrivialTheme.colors.tertiary,
                    selectedLabelColor = TrivialTheme.colors.onTertiary
                ),
                onClick = { onCategorySelected(category.id) })
        }
        item(span = { GridItemSpan(2) }) {
            TrivialButton(
                text = "Select",
                containerColor = TrivialTheme.colors.secondary,
                onClick = onDismiss
            )
        }
    }
}

@Preview
@Composable
fun CategoryBottomSheetContentPreview() {
    CategoryBottomSheetContent(selectedCategory = -1, onCategorySelected = {}, onDismiss = {})
}

@Preview()
@Composable
private fun QuizSetupScreenPreview() {
    TrivialTheme {
        QuizSetupScreen(onStartQuizClick = {})
    }
}

object Categories {
    val list = listOf(
        TriviaCategory(9, "General Knowledge", icon = "\uD83D\uDDFA\uFE0F"),
        TriviaCategory(10, "Books", icon = "\uD83D\uDCDA\uFE0F"),
        TriviaCategory(11, "Film", icon = "\uD83C\uDFAC"),
        TriviaCategory(12, "Music", icon = "\uD83C\uDFB8\uFE0F"),
        TriviaCategory(13, "Musicals & Theatres", icon = "\uD83C\uDFAD"),
        TriviaCategory(14, "Television", icon = "\uD83D\uDCFA\uFE0F"),
        TriviaCategory(15, "Video Games", icon = "\uD83C\uDFAE\uFE0F"),
        TriviaCategory(16, "Board Games", icon = "\uD83C\uDFB2"),
        TriviaCategory(17, "Science & Nature", icon = "\uD83D\uDD2C"),
        TriviaCategory(18, "Computers", icon = "\uD83D\uDCBB\uFE0F"),
        TriviaCategory(19, "Mathematics", icon = "\uD83E\uDDEE"),
        TriviaCategory(20, "Mythology", icon = "\uD83E\uDEBD"),
        TriviaCategory(21, "Sports", icon = "\uD83C\uDFC5"),
        TriviaCategory(22, "Geography", icon = "\uD83C\uDF0E"),
        TriviaCategory(23, "History", icon = "\uD83D\uDCDC"),
        TriviaCategory(24, "Politics", icon = "\uD83D\uDDF3\uFE0F"),
        TriviaCategory(25, "Art", icon = "\uD83D\uDDBC\uFE0F"),
        TriviaCategory(26, "Celebrities", icon = "\uD83D\uDCF8"),
        TriviaCategory(27, "Animals", icon = "\uD83D\uDC31"),
        TriviaCategory(28, "Vehicles", icon = "\uD83D\uDE97\uFE0F"),
        TriviaCategory(29, "Comics", icon = "\uD83D\uDDEF\uFE0F"),
        TriviaCategory(30, "Science Gadgets", icon = "\uD83D\uDD0E"),
        TriviaCategory(31, "Japanese Anime & Manga", icon = "\uD83C\uDDEF\uD83C\uDDF5"),
        TriviaCategory(32, "Cartoon & Animations", icon = "\uD83D\uDCA8"),
    )
}
