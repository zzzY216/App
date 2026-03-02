package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BiliGetVideoDetailRepository
import com.software.app.domain.model.bili.VideoDetailDomain
import javax.inject.Inject

class BiliGetVideoDetailUseCase @Inject constructor(
    private val biliGetVideoDetailRepository: BiliGetVideoDetailRepository
) {
    suspend operator fun invoke(
        aid: String? = null,
        bvid: String? = null
    ): Result<VideoDetailDomain> {
        return biliGetVideoDetailRepository.getVideoDetail(aid, bvid)
    }
}