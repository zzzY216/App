package com.software.app.di.room

import android.content.Context
import com.software.app.data.local.room.LessonDatabase
import com.software.app.data.local.room.dao.LessonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoom(
        @ApplicationContext context: Context
    ): LessonDatabase {
        return LessonDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideLessonDao(database: LessonDatabase): LessonDao {
        return database.lessonDao()
    }
}