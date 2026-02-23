package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BiliGetVideoPlayUrlRepository
import com.software.app.domain.model.bili.PlayUrlDataDomain
import javax.inject.Inject

class BiliGetVideoPlayUrlUseCase @Inject constructor(
    private val biliGetVideoPlayUrlRepository: BiliGetVideoPlayUrlRepository
) {
    suspend operator fun invoke(
        avid: String,
        cid: String,
        qn: Int,
        type: String,
        platform: String
    ): Result<PlayUrlDataDomain> {
        return biliGetVideoPlayUrlRepository.getVideoPlayUrl(avid, cid, qn, type, platform)
    }
}