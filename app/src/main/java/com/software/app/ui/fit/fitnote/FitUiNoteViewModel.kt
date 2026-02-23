package com.software.app.ui.fit.fitnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.app.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitUiNoteViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    val _uiState = MutableStateFlow<FitUiState>(FitUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<UiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()
    fun onEvent(event: FitUiNoteEvent) {
        when (event) {
            is FitUiNoteEvent.UpdateTaskState -> updateTaskState(event.taskId, event.taskState)
            is FitUiNoteEvent.UpdateTaskTitle -> updateTaskTitle(event.taskId, event.taskTitle)
            is FitUiNoteEvent.UpdateIsAddingTask -> updateIsAddingTask(event.isAddingTask)
            FitUiNoteEvent.AddTask -> addTask()
            is FitUiNoteEvent.UpdateCurrentTaskTitle -> updateCurrentTaskTitle(event.taskTitle)
            FitUiNoteEvent.GetAllTasks -> getAllTasks()
        }
    }

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks().collect { taskList ->
                _uiState.update {
                    it.copy(tasks = taskList)
                }
            }
        }
    }

    private fun updateCurrentTaskTitle(taskTitle: String) {
        _uiState.update {
            it.copy(currentTaskTitle = taskTitle)
        }
    }

    private fun addTask() {
        viewModelScope.launch {
            if (_uiState.value.currentTaskTitle.isEmpty()) {
                _uiEffect.send(UiEffect.ShowToast("任务标题不能为空"))
                return@launch
            }
            try {
                taskRepository.addTask(_uiState.value.currentTaskTitle)
                _uiEffect.send(UiEffect.ShowToast("任务添加成功"))
                _uiState.update {
                    it.copy(currentTaskTitle = "", isAddingTask = false)
                }
            } catch (e: Exception) {
                _uiEffect.send(UiEffect.ShowToast("任务添加失败"))
            }
        }
    }

    private fun updateIsAddingTask(isAddingTask: Boolean) {
        _uiState.update {
            it.copy(isAddingTask = isAddingTask)
        }
    }

    private fun updateTaskState(taskId: String, isDone: Boolean) {
        viewModelScope.launch {
            taskRepository.updateTaskStatus(taskId, isDone)
        }
    }

    private fun updateTaskTitle(taskId: String, newTitle: String) {
        viewModelScope.launch {
            taskRepository.updateTaskTitle(taskId, newTitle)
        }
    }
}