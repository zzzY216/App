package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BiliGetUserInfoRepository
import com.software.app.domain.model.bili.UserInfoDomain
import javax.inject.Inject

class BiliGetUserInfoUseCase @Inject constructor(
    private val biliGetUserInfoRepository: BiliGetUserInfoRepository
) {
    suspend operator fun invoke(cookie: String): Result<UserInfoDomain> {
        return biliGetUserInfoRepository.getUserInfo(cookie)
    }
}