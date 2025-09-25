package com.example.trivial.feature.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trivial.core.common.TriviaCategories
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
    startQuiz: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isReadyToPlay) {
        startQuiz()
    }

    QuizSetupScreen(
        modifier = modifier,
        uiState = uiState,
        onQuizSetupAction = viewModel::onQuizSetupAction,
        onPlayClick = viewModel::onPlayClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuizSetupScreen(
    modifier: Modifier = Modifier,
    uiState: QuizUiState,
    onQuizSetupAction: (QuizSetupAction) -> Unit,
    onPlayClick: () -> Unit,
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // TODO: Divide into different composables
    // TODO: Display error message
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
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
                selectedOption = uiState.selectedDifficulty.description,
                options = TriviaDifficulty.entries.map { it.description }
            ) { onQuizSetupAction(QuizSetupAction.OnDifficultyChanged(TriviaDifficulty.fromString(it))) }
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
                    text = if (uiState.selectedCategory == TriviaCategory.DEFAULT) {
                        "Select category"
                    } else {
                        uiState.selectedCategory.name
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = TrivialTheme.colors.neutralBlack
                )
                Spacer(modifier = Modifier.width(TrivialSize.SizeMedium))
                Icon(
                    modifier = Modifier.padding(TrivialSize.SizeMedium),
                    painter = painterResource(if (uiState.selectedCategory == TriviaCategory.DEFAULT) uiResources.drawable.chevron_right else uiResources.drawable.edit),
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
                selectedOption = uiState.selectedType.description,
                options = TriviaQuestionType.entries.map { it.description }
            ) { onQuizSetupAction(QuizSetupAction.OnTypeChanged(TriviaQuestionType.fromString(it))) }
            Text(
                text = "Number of questions",
                style = MaterialTheme.typography.titleLarge,
                color = TrivialTheme.colors.onPrimary
            )
            TrivialCounter(
                modifier = Modifier.padding(vertical = TrivialSize.SizeMedium),
                count = uiState.numberOfQuestions,
                min = 2
            ) {
                onQuizSetupAction(QuizSetupAction.OnAmountChanged(it))
            }
            Spacer(modifier = Modifier.weight(1f))
            TrivialButton(
                text = "PLAY",
                enabled = uiState.selectedCategory != TriviaCategory.DEFAULT,
                containerColor = TrivialTheme.colors.secondary,
                onClick = onPlayClick
            )
        }
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            containerColor = TrivialTheme.colors.neutralWhite,
            onDismissRequest = { openBottomSheet = false },
        ) {
            CategoryBottomSheetContent(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = {
                    onQuizSetupAction(QuizSetupAction.OnCategoryChanged(it))
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
    selectedCategory: TriviaCategory,
    onCategorySelected: (category: TriviaCategory) -> Unit,
    onDismiss: () -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(TrivialSize.SizeSmall),
        verticalArrangement = Arrangement.spacedBy(TrivialSize.SizeSmall),
        contentPadding = PaddingValues(TrivialSize.SizeMedium)
    ) {
        items(TriviaCategories.list) { category ->
            FilterChip(
                selected = category == selectedCategory,
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
                onClick = { onCategorySelected(category) })
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
    CategoryBottomSheetContent(
        selectedCategory = TriviaCategory.DEFAULT,
        onCategorySelected = {},
        onDismiss = {})
}

@Preview()
@Composable
private fun QuizSetupScreenPreview() {
    TrivialTheme {
        QuizSetupScreen(
            uiState = QuizUiState(),
            onQuizSetupAction = {},
            onPlayClick = {},
        )
    }
}

