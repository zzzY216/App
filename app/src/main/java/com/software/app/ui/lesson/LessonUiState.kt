package com.software.app.ui.lesson

import com.software.app.data.local.room.entity.Lesson

data class LessonUiState(
    val lessons: List<Lesson> = emptyList(),
)