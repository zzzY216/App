package com.software.app.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.software.app.data.local.room.entity.Lesson
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lesson")
    fun getAllLessons(): Flow<List<Lesson>>

    @Query("SELECT * FROM lesson WHERE dayOfWeek = :day")
    fun getLessonsByDay(day: Int): Flow<List<Lesson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)

    @Query("DELETE FROM lesson WHERE id = :id")
    suspend fun deleteLessonById(id: Int)
}