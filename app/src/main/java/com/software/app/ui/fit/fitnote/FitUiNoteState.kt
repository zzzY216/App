package com.software.app.ui.fit.fitnote

import com.software.app.domain.model.Task

data class FitUiState(
    val tasks: List<Task> = emptyList(),
    val currentTaskTitle: String = "",
    val isAddingTask: Boolean = false,
)

sealed class UiEffect {
    data class ShowToast(val message: String) : UiEffect()
}