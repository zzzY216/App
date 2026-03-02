package com.software.app.data.repository.blbl

import com.software.app.data.remote.model.blbl.VideoDetail
import com.software.app.data.remote.model.blbl.toDomain
import com.software.app.data.remote.network.BiliApiService
import com.software.app.domain.model.bili.VideoDetailDomain
import javax.inject.Inject

interface BiliGetVideoDetailRepository {
    suspend fun getVideoDetail(
        aid: String? = null,
        bvid: String? = null
    ): Result<VideoDetailDomain>
}

class BiliGetVideoDetailRepositoryImpl @Inject constructor(
    private val apiService: BiliApiService
): BiliGetVideoDetailRepository {
    override suspend fun getVideoDetail(
        aid: String?,
        bvid: String?
    ): Result<VideoDetailDomain> {
        return try {
            val response = apiService.getVideoDetail(aid, bvid)
            if (response.code == 0) {
                if (response.data != null) {
                    Result.success(response.data.toDomain())
                } else {
                    Result.failure(Exception("Data is null"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code} - ${response.message}"))
            }
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}