package com.software.app.data.repository.room

import com.software.app.data.local.room.dao.LessonDao
import com.software.app.data.local.room.entity.Lesson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LessonRepository {
    fun getAllLessons(): Flow<List<Lesson>>
    suspend fun getLessonsByDay(day: Int): Flow<List<Lesson>>
    suspend fun insertLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(lesson: Lesson)
    suspend fun deleteLessonById(id: Int)
}

class LessonRepositoryImpl @Inject constructor(
    private val lessonDao: LessonDao
) : LessonRepository {
    override fun getAllLessons(): Flow<List<Lesson>> {
        return lessonDao.getAllLessons()
    }

    override suspend fun getLessonsByDay(day: Int): Flow<List<Lesson>> {
        return lessonDao.getLessonsByDay(day)
    }

    override suspend fun insertLesson(lesson: Lesson) {
        return lessonDao.insertLesson(lesson)
    }

    override suspend fun updateLesson(lesson: Lesson) {
        return lessonDao.updateLesson(lesson)
    }

    override suspend fun deleteLesson(lesson: Lesson) {
        return lessonDao.deleteLesson(lesson)
    }

    override suspend fun deleteLessonById(id: Int) {
        return lessonDao.deleteLessonById(id)
    }
}