package com.software.app.di.room

import com.software.app.data.local.room.dao.LessonDao
import com.software.app.data.repository.room.LessonRepository
import com.software.app.data.repository.room.LessonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomRepositoryModule {
    @Provides
    @Singleton
    fun provideBiliLessonRepository(
        lessonDao: LessonDao
    ): LessonRepository {
        return LessonRepositoryImpl(lessonDao)
    }
}