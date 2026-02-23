package com.software.app.di

import com.software.app.data.repository.FitPersonRepository
import com.software.app.data.repository.FitPersonRepositoryImpl
import com.software.app.data.repository.TaskRepository
import com.software.app.data.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTaskRepository(
        realm: Realm
    ): TaskRepository {
        return TaskRepositoryImpl(realm)
    }

    @Provides
    @Singleton
    fun provideFitPersonRepository(
        realm: Realm
    ): FitPersonRepository {
        return FitPersonRepositoryImpl(realm)
    }
}