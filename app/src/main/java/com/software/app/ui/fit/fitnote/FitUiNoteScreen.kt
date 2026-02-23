package com.software.app.ui.fit.fitnote

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.software.app.domain.model.Task

@OptIn(
    ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun FitUiNoteScreen(
    viewModel: FitUiNoteViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val isExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                item {
                    if (uiState.value.tasks.isEmpty()) {
                        FitUiEmpty()
                    } else {
                        uiState.value.tasks.forEach { task ->
                            FitUiItem(task = task, onEvent = viewModel::onEvent)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            HorizontalFloatingToolBarItem(
                listState = listState,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onEvent = viewModel::onEvent
            )
            if (uiState.value.isAddingTask) {
                ModalBottomSheet(
                    onDismissRequest = {
                        viewModel.onEvent(FitUiNoteEvent.UpdateIsAddingTask(false))
                    }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = uiState.value.currentTaskTitle,
                            onValueChange = {
                                viewModel.onEvent(FitUiNoteEvent.UpdateCurrentTaskTitle(it))
                            },
                            label = { Text("任务标题") }
                        )
                        OutlinedButton(
                            onClick = { viewModel.onEvent(FitUiNoteEvent.AddTask) }
                        ) {
                            Text("添加")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HorizontalFloatingToolBarItem(
    listState: LazyListState,
    modifier: Modifier,
    onEvent: (FitUiNoteEvent) -> Unit
) {
    HorizontalFloatingToolbar(
        floatingActionButton = {
            TooltipBox(
                positionProvider =
                    TooltipDefaults.rememberTooltipPositionProvider(
                        TooltipAnchorPosition.Above
                    ),
                tooltip = { PlainTooltip { Text("Localized description") } },
                state = rememberTooltipState(),
            ) {
                FloatingToolbarDefaults.VibrantFloatingActionButton(
                    onClick = {
                        onEvent(FitUiNoteEvent.UpdateIsAddingTask(true))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        },
        expanded = !listState.isScrollInProgress,
        modifier = modifier,
        content = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    TooltipAnchorPosition.Above
                ),
                tooltip = {
                    PlainTooltip { Text("Localized description") }
                },
                state = rememberTooltipState(),
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
            }
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    TooltipAnchorPosition.Above
                ),
                tooltip = {
                    PlainTooltip { Text("Localized description") }
                },
                state = rememberTooltipState()
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(Icons.Default.Person, contentDescription = "Delete")
                }
            }
        }
    )
}

@Composable
fun FitUiItem(
    task: Task,
    onEvent: (FitUiNoteEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (task.isDone) "Completed" else "Not Completed",
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    modifier = Modifier.weight(1f),
                    checked = task.isDone,
                    onCheckedChange = {
                        onEvent(
                            FitUiNoteEvent.UpdateTaskState(
                                taskId = task.id,
                                taskState = it
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun FitUiEmpty() {
    Text(text = "No tasks")
}