package com.software.app.di

import com.software.app.data.local.mongo.TaskEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                TaskEntity::class
            )
        )
            .schemaVersion(1)
            .build()
        return Realm.open(config)
    }
}