package com.software.app.ui.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.app.data.local.room.entity.Lesson
import com.software.app.data.repository.room.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LessonUiViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : ViewModel() {

    val lessons: StateFlow<List<Lesson>> = lessonRepository.getAllLessons().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}