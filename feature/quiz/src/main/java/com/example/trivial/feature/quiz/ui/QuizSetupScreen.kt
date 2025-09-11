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
import com.example.trivial.core.common.Category
import com.example.trivial.core.common.Difficulty
import com.example.trivial.core.common.QuestionType
import com.example.trivial.ui.R as uiResources
import com.example.trivial.ui.components.ButtonPrimary
import com.example.trivial.ui.components.Counter
import com.example.trivial.ui.components.OptionsSelector
import com.example.trivial.ui.theme.Size
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
    var difficulty by remember { mutableStateOf(Difficulty.Medium) }
    var category by remember { mutableIntStateOf(-1) }
    var type by remember { mutableStateOf(QuestionType.MultipleChoice) }
    var amount by remember { mutableIntStateOf(10) }
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Size.SizeMedium)
    ) {
        Text(
            text = "Quiz Setup",
            style = MaterialTheme.typography.headlineLarge,
            color = TrivialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(Size.SizeLarge))
        Text(
            text = "Difficulty",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        OptionsSelector(
            modifier = Modifier.padding(vertical = Size.SizeMedium),
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
                .padding(vertical = Size.SizeMedium)
                .background(TrivialTheme.colors.neutralWhite)
                .clickable { openBottomSheet = true },
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(Size.SizeMedium),
                text = if (category == -1) "Select category" else Categories.list.first { it.id == category }.name,
                style = MaterialTheme.typography.labelLarge,
                color = TrivialTheme.colors.neutralBlack
            )
            Spacer(modifier = Modifier.width(Size.SizeMedium))
            Icon(
                modifier = Modifier.padding(Size.SizeMedium),
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
        OptionsSelector(
            modifier = Modifier.padding(vertical = Size.SizeMedium),
            selectedOption = "Multiple Choice",
            options = listOf("Multiple Choice", "True / False")
        ) { }
        Text(
            text = "Number of questions",
            style = MaterialTheme.typography.titleLarge,
            color = TrivialTheme.colors.onPrimary
        )
        Counter(modifier = Modifier.padding(vertical = Size.SizeMedium), count = amount, min = 2) {
            amount = it
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(text = "PLAY", containerColor = TrivialTheme.colors.secondary) { }
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
        horizontalArrangement = Arrangement.spacedBy(Size.SizeSmall),
        verticalArrangement = Arrangement.spacedBy(Size.SizeSmall),
        contentPadding = PaddingValues(Size.SizeMedium)
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
            ButtonPrimary(
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
        Category(9, "General Knowledge", icon = "\uD83D\uDDFA\uFE0F"),
        Category(10, "Books", icon = "\uD83D\uDCDA\uFE0F"),
        Category(11, "Film", icon = "\uD83C\uDFAC"),
        Category(12, "Music", icon = "\uD83C\uDFB8\uFE0F"),
        Category(13, "Musicals & Theatres", icon = "\uD83C\uDFAD"),
        Category(14, "Television", icon = "\uD83D\uDCFA\uFE0F"),
        Category(15, "Video Games", icon = "\uD83C\uDFAE\uFE0F"),
        Category(16, "Board Games", icon = "\uD83C\uDFB2"),
        Category(17, "Science & Nature", icon = "\uD83D\uDD2C"),
        Category(18, "Computers", icon = "\uD83D\uDCBB\uFE0F"),
        Category(19, "Mathematics", icon = "\uD83E\uDDEE"),
        Category(20, "Mythology", icon = "\uD83E\uDEBD"),
        Category(21, "Sports", icon = "\uD83C\uDFC5"),
        Category(22, "Geography", icon = "\uD83C\uDF0E"),
        Category(23, "History", icon = "\uD83D\uDCDC"),
        Category(24, "Politics", icon = "\uD83D\uDDF3\uFE0F"),
        Category(25, "Art", icon = "\uD83D\uDDBC\uFE0F"),
        Category(26, "Celebrities", icon = "\uD83D\uDCF8"),
        Category(27, "Animals", icon = "\uD83D\uDC31"),
        Category(28, "Vehicles", icon = "\uD83D\uDE97\uFE0F"),
        Category(29, "Comics", icon = "\uD83D\uDDEF\uFE0F"),
        Category(30, "Science Gadgets", icon = "\uD83D\uDD0E"),
        Category(31, "Japanese Anime & Manga", icon = "\uD83C\uDDEF\uD83C\uDDF5"),
        Category(32, "Cartoon & Animations", icon = "\uD83D\uDCA8"),
    )
}
