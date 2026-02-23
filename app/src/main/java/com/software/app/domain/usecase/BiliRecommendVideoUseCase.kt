package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BiliRecommendVideoRepository
import com.software.app.domain.model.bili.RecommendDataDomain
import javax.inject.Inject

class BiliRecommendVideoUseCase @Inject constructor(
    private val biliRecommendVideoRepository: BiliRecommendVideoRepository
) {
    suspend operator fun invoke(
        idx: Long,
        pull: Boolean,
        loginEvent: Int,
        flush: Int
    ): Result<RecommendDataDomain> {
        return biliRecommendVideoRepository.getRecommendVideo(idx, pull, loginEvent, flush)
    }
}