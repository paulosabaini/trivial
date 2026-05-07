package com.example.trivial.feature.quiz.ui.setup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trivial.core.common.TriviaCategories
import com.example.trivial.core.common.TriviaCategory
import com.example.trivial.core.common.TriviaDifficulty
import com.example.trivial.core.common.TriviaQuestionType
import com.example.trivial.feature.quiz.R
import com.example.trivial.ui.R as uiResources
import com.example.trivial.ui.components.TrivialButton
import com.example.trivial.ui.components.TrivialCounter
import com.example.trivial.ui.components.TrivialOptionsSelector
import com.example.trivial.ui.components.TrivialTopAppBar
import com.example.trivial.ui.theme.TrivialSize
import com.example.trivial.ui.theme.TrivialTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun QuizRoute(
    onBack: () -> Unit,
    startQuiz: (categoryId: Int, difficulty: String, questionType: String) -> Unit
) {
    val viewModel: QuizSetupViewModel = koinViewModel<QuizSetupViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentOnStartQuiz by rememberUpdatedState(startQuiz)

    if (uiState.isReadyToPlay) {
        currentOnStartQuiz(
            uiState.selectedCategory.id,
            uiState.selectedDifficulty.name,
            uiState.selectedType.name
        )
    }

    QuizSetupScreen(
        uiState = uiState,
        onQuizSetupAction = viewModel::onQuizSetupAction,
        onPlayClick = viewModel::onPlayClick,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuizSetupScreen(
    uiState: QuizSetupUIState,
    onQuizSetupAction: (QuizSetupAction) -> Unit,
    onPlayClick: () -> Unit,
    onBack: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = { TrivialTopAppBar(showBack = true, onBack = onBack) },
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.padding(paddingValues))
            }

            uiState.error != null -> {
                ErrorMessage(modifier = Modifier.padding(paddingValues), message = uiState.error)
            }

            else -> {
                ScreenContent(
                    modifier = Modifier.padding(paddingValues),
                    uiState = uiState,
                    openBottomSheet = { onQuizSetupAction(QuizSetupAction.OpenCategoryBottomSheet) },
                    onQuizSetupAction = onQuizSetupAction,
                    onPlayClick = onPlayClick
                )
            }
        }
    }

    if (uiState.isCategoryBottomSheetVisible) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            containerColor = TrivialTheme.colors.background,
            onDismissRequest = { onQuizSetupAction(QuizSetupAction.DismissCategoryBottomSheet) },
        ) {
            CategoryBottomSheetContent(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = {
                    onQuizSetupAction(QuizSetupAction.OnCategoryChanged(it))
                },
                onDismiss = {
                    onQuizSetupAction(QuizSetupAction.DismissCategoryBottomSheet)
                }
            )
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = TrivialTheme.colors.primary)
    }
}

@Composable
fun ErrorMessage(modifier: Modifier = Modifier, message: String) {
    Card(
        modifier = modifier.padding(TrivialSize.SizeMedium),
        colors = CardDefaults.cardColors(
            containerColor = TrivialTheme.colors.error,
            contentColor = TrivialTheme.colors.onError
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = TrivialSize.SizeNone)
    ) {
        Text(
            modifier = Modifier.padding(TrivialSize.SizeMedium),
            text = message,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    uiState: QuizSetupUIState,
    openBottomSheet: (Boolean) -> Unit,
    onQuizSetupAction: (QuizSetupAction) -> Unit,
    onPlayClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TrivialTheme.colors.background)
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.quiz_setup).uppercase(),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Light),
            color = TrivialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))

        SetupSection(title = stringResource(R.string.difficulty)) {
            TrivialOptionsSelector(
                modifier = Modifier.fillMaxWidth(),
                selectedOption = uiState.selectedDifficulty.description,
                options = TriviaDifficulty.entries.map { it.description }
            ) { onQuizSetupAction(QuizSetupAction.OnDifficultyChanged(TriviaDifficulty.fromString(it))) }
        }

        SetupSection(title = stringResource(R.string.category)) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openBottomSheet(true) },
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, TrivialTheme.colors.gray300),
                color = TrivialTheme.colors.background
            ) {
                Row(
                    modifier = Modifier.padding(TrivialSize.SizeMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (uiState.selectedCategory == TriviaCategory.DEFAULT) {
                            stringResource(R.string.select_category)
                        } else {
                            uiState.selectedCategory.name
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = TrivialTheme.colors.onBackground
                    )
                    Icon(
                        painter = painterResource(if (uiState.selectedCategory == TriviaCategory.DEFAULT) uiResources.drawable.chevron_right else uiResources.drawable.edit),
                        tint = TrivialTheme.colors.onBackground,
                        contentDescription = null,
                    )
                }
            }
        }

        SetupSection(title = "Type") {
            TrivialOptionsSelector(
                modifier = Modifier.fillMaxWidth(),
                selectedOption = uiState.selectedType.description,
                options = TriviaQuestionType.entries.map { it.description }
            ) { onQuizSetupAction(QuizSetupAction.OnTypeChanged(TriviaQuestionType.fromString(it))) }
        }

        SetupSection(title = stringResource(R.string.number_of_questions)) {
            TrivialCounter(
                count = uiState.numberOfQuestions,
                min = 2
            ) {
                onQuizSetupAction(QuizSetupAction.OnAmountChanged(it))
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        TrivialButton(
            modifier = Modifier.height(TrivialSize.SizeExtraExtraLarge),
            text = stringResource(R.string.play),
            enabled = uiState.selectedCategory != TriviaCategory.DEFAULT,
            onClick = onPlayClick
        )
    }
}

@Composable
private fun SetupSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
            color = TrivialTheme.colors.gray600,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
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
        verticalArrangement = Arrangement.spacedBy(TrivialSize.SizeExtraSmall),
        contentPadding = PaddingValues(TrivialSize.SizeMedium)
    ) {
        items(TriviaCategories.list, key = { it.id }) { category ->
            FilterChip(
                selected = category == selectedCategory,
                label = { Text(category.name, style = MaterialTheme.typography.labelLarge) },
                leadingIcon = category.icon?.let { icon ->
                    { Text(icon) }
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = TrivialTheme.colors.surface,
                    labelColor = TrivialTheme.colors.onSurface,
                    selectedContainerColor = TrivialTheme.colors.primary,
                    selectedLabelColor = TrivialTheme.colors.onPrimary
                ),
                border = null,
                shape = RectangleShape,
                onClick = { onCategorySelected(category) }
            )
        }
        item(span = { GridItemSpan(2) }) {
            TrivialButton(
                text = stringResource(R.string.select),
                containerColor = TrivialTheme.colors.secondary,
                contentColor = TrivialTheme.colors.onSecondary,
                onClick = onDismiss
            )
        }
    }
}

@Preview
@Composable
private fun CategoryBottomSheetContentPreview() {
    TrivialTheme {
        CategoryBottomSheetContent(
            selectedCategory = TriviaCategory.DEFAULT,
            onCategorySelected = {},
            onDismiss = {})
    }
}

@Preview()
@Composable
private fun QuizSetupScreenPreview() {
    TrivialTheme {
        QuizSetupScreen(
            uiState = QuizSetupUIState(),
            onQuizSetupAction = {},
            onPlayClick = {},
            onBack = {}
        )
    }
}
