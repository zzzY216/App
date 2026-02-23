package com.software.app.data.repository.blbl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.software.app.data.remote.model.blbl.toDomain
import com.software.app.data.remote.network.BiliApiService
import com.software.app.data.remote.paging.BiliRecommendPagingSource
import com.software.app.domain.model.bili.RecommendDataDomain
import com.software.app.domain.model.bili.RecommendItemDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BiliRecommendVideoRepository {
    suspend fun getRecommendVideo(
        idx: Long,
        pull: Boolean,
        loginEvent: Int,
        flush: Int
    ): Result<RecommendDataDomain>

    fun getRecommendVideoPagingFlow(): Flow<PagingData<RecommendItemDomain>>
}

class BiliRecommendVideoRepositoryImpl @Inject constructor(
    private val apiService: BiliApiService
) : BiliRecommendVideoRepository {
    override suspend fun getRecommendVideo(
        idx: Long,
        pull: Boolean,
        loginEvent: Int,
        flush: Int
    ): Result<RecommendDataDomain> {
        return try {
            val response = apiService.getRecommendList(
                idx = idx,
                pull = pull,
                loginEvent = loginEvent,
                flush = flush
            )
            if (response.code == 0) {
                if (response.data != null) {
                    Result.success(response.data.toDomain())
                } else {
                    Result.failure(Exception("Data is null"))
                }
            } else {
                Result.failure(Exception("Code is not 0"))
            }
        } catch (e: Exception) {
            Log.e("BiliRecommendVideoRepository", "Error: ${e.message}")
            Result.failure(e)
        }
    }

    override fun getRecommendVideoPagingFlow(): Flow<PagingData<RecommendItemDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 3
            ),
            pagingSourceFactory = {
                BiliRecommendPagingSource(apiService)
            }
        ).flow
    }
}