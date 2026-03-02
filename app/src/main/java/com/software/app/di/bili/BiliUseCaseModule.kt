package com.software.app.di.bili

import com.software.app.data.repository.blbl.BiliGetUserInfoRepository
import com.software.app.data.repository.blbl.BiliGetVideoDetailRepository
import com.software.app.data.repository.blbl.BiliGetVideoPlayUrlRepository
import com.software.app.data.repository.blbl.BiliRecommendVideoRepository
import com.software.app.data.repository.blbl.BlBlPollQrCodeStatusRepository
import com.software.app.data.repository.blbl.BlBlQrCodeDataRepository
import com.software.app.domain.usecase.BiliGetUserInfoUseCase
import com.software.app.domain.usecase.BiliGetVideoDetailUseCase
import com.software.app.domain.usecase.BiliGetVideoPlayUrlUseCase
import com.software.app.domain.usecase.BiliPollQrCodeStatusUseCase
import com.software.app.domain.usecase.BiliQrCodeDataUseCase
import com.software.app.domain.usecase.BiliRecommendVideoUseCase
import com.software.app.domain.usecase.GetRecommendVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BiliUseCaseModule {
    @Provides
    fun provideBiliGetVideoPlayUrlUseCase(
        biliGetVideoPlayUrlRepository: BiliGetVideoPlayUrlRepository
    ): BiliGetVideoPlayUrlUseCase {
        return BiliGetVideoPlayUrlUseCase(biliGetVideoPlayUrlRepository)
    }

    @Provides
    fun provideBiliQrCodeDataUseCase(
        biliQrCodeRepository: BlBlQrCodeDataRepository
    ): BiliQrCodeDataUseCase {
        return BiliQrCodeDataUseCase(biliQrCodeRepository)
    }

    @Provides
    fun provideBiliPollQrCodeStatusUseCase(
        biliPollQrCodeStatusRepository: BlBlPollQrCodeStatusRepository
    ): BiliPollQrCodeStatusUseCase {
        return BiliPollQrCodeStatusUseCase(biliPollQrCodeStatusRepository)
    }

    @Provides
    fun provideBiliRecommendVideoUseCase(
        biliRecommendVideoRepository: BiliRecommendVideoRepository
    ): BiliRecommendVideoUseCase {
        return BiliRecommendVideoUseCase(biliRecommendVideoRepository)
    }

    @Provides
    fun provideBiliRecommendVideoPagingUseCase(
        biliRecommendVideoRepository: BiliRecommendVideoRepository
    ): GetRecommendVideosUseCase {
        return GetRecommendVideosUseCase(biliRecommendVideoRepository)
    }

    @Provides
    fun provideBiliGetVideoDetailUseCase(
        biliGetVideoDetailRepository: BiliGetVideoDetailRepository
    ): BiliGetVideoDetailUseCase {
        return BiliGetVideoDetailUseCase(biliGetVideoDetailRepository)
    }

    @Provides
    fun provideBiliGetUserInfoUseCase(
        biliGetUserInfoRepository: BiliGetUserInfoRepository
    ): BiliGetUserInfoUseCase {
        return BiliGetUserInfoUseCase(biliGetUserInfoRepository)
    }
}