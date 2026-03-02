package com.software.app.di.bili

import com.software.app.data.remote.network.BiliApiService
import com.software.app.data.remote.network.BiliLoginApiService
import com.software.app.data.repository.blbl.BiliGetUserInfoRepository
import com.software.app.data.repository.blbl.BiliGetUserInfoRepositoryImpl
import com.software.app.data.repository.blbl.BiliGetVideoDetailRepository
import com.software.app.data.repository.blbl.BiliGetVideoDetailRepositoryImpl
import com.software.app.data.repository.blbl.BiliGetVideoPlayUrlRepository
import com.software.app.data.repository.blbl.BiliGetVideoPlayUrlRepositoryImpl
import com.software.app.data.repository.blbl.BiliRecommendVideoRepository
import com.software.app.data.repository.blbl.BiliRecommendVideoRepositoryImpl
import com.software.app.data.repository.blbl.BlBlPollQrCodeStatusRepository
import com.software.app.data.repository.blbl.BlBlPollQrCodeStatusRepositoryImpl
import com.software.app.data.repository.blbl.BlBlQrCodeDataRepository
import com.software.app.data.repository.blbl.BlBlQrCodeDataRepositoryImpl
import com.software.app.di.BiliAppNetwork
import com.software.app.di.BiliLoginNetwork
import com.software.app.di.BiliApiNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BiliRepositoryModule {
    @Provides
    @Singleton
    fun provideBiliGetVideoPlayUrlRepository(
        @BiliApiNetwork apiService: BiliApiService
    ): BiliGetVideoPlayUrlRepository {
        return BiliGetVideoPlayUrlRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBlBlQrCodeDataRepository(
        @BiliLoginNetwork apiService: BiliLoginApiService
    ): BlBlQrCodeDataRepository {
        return BlBlQrCodeDataRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBlBlpollQrCodeStatusRepository(
        @BiliLoginNetwork apiService: BiliLoginApiService
    ): BlBlPollQrCodeStatusRepository {
        return BlBlPollQrCodeStatusRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBiliRecommendVideoRepository(
        @BiliAppNetwork apiService: BiliApiService
    ): BiliRecommendVideoRepository {
        return BiliRecommendVideoRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBiliGetVideoDetailRepository(
        @BiliApiNetwork apiService: BiliApiService
    ): BiliGetVideoDetailRepository {
        return BiliGetVideoDetailRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBiliGetUserInfoRepository(
        @BiliApiNetwork apiService: BiliApiService
    ): BiliGetUserInfoRepository {
        return BiliGetUserInfoRepositoryImpl(apiService)
    }
}