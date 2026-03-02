package com.software.app.data.repository.blbl

import com.software.app.data.remote.model.blbl.toDomain
import com.software.app.data.remote.network.BiliApiService
import com.software.app.domain.model.bili.UserInfoDomain
import javax.inject.Inject

interface BiliGetUserInfoRepository {
    suspend fun getUserInfo(cookie: String): Result<UserInfoDomain>
}

class BiliGetUserInfoRepositoryImpl @Inject constructor(
    private val apiService: BiliApiService
) : BiliGetUserInfoRepository {
    override suspend fun getUserInfo(cookie: String): Result<UserInfoDomain> {
        return try {
            val response = apiService.getUserInfo(cookie)
            if (response.code == 0) {
               if (response.data != null) {
                   Result.success(response.data.toDomain())
               } else {
                   Result.failure(Exception("Failed to get user info"))
               }
            } else {
                Result.failure(Exception("Failed to get user info"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}