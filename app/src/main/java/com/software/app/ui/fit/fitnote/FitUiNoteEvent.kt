package com.software.app.ui.fit.fitnote

sealed class FitUiNoteEvent {
    data class UpdateTaskTitle(val taskId: String, val taskTitle: String) : FitUiNoteEvent()
    data class UpdateTaskState(val taskId: String, val taskState: Boolean) : FitUiNoteEvent()
    object AddTask : FitUiNoteEvent()
    object GetAllTasks : FitUiNoteEvent()
    data class UpdateCurrentTaskTitle(val taskTitle: String) : FitUiNoteEvent()
    data class UpdateIsAddingTask(val isAddingTask: Boolean) : FitUiNoteEvent()
}