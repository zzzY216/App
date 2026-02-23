package com.software.app.di.bili

import android.content.Context
import com.software.app.data.local.mongo.bili.BiliSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BiliDataModule {
    @Provides
    @Singleton
    fun provideBiliSessionManager(
        @ApplicationContext context: Context
    ): BiliSessionManager {
        return BiliSessionManager(context)
    }
}