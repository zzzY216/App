package com.software.app.data.remote.model.blbl

import com.software.app.domain.model.bili.RecommendConfigDomain
import com.software.app.domain.model.bili.RecommendDataDomain
import com.software.app.domain.model.bili.RecommendItemDomain
import com.software.app.domain.model.bili.RecommendResponseDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendResponse(
    val code: Int,
    val message: String,
    val tt1: Int,
    val data: RecommendData
)

@Serializable
data class RecommendData(
    val config: RecommendConfig? = null,
    val items: List<RecommendItem>
)

@Serializable
data class RecommendItem(
    val idx: Long,
    val cover: String,
    val title: String,
    val uri: String,
    @SerialName("cover_left_1_content_description") val coverLeft1ContentDescription: String?, // 观看次数
    @SerialName("cover_left_2_content_description") val coverLeft2ContentDescription: String?,// 弹幕数据
    @SerialName("cover_right_content_description") val coverRightContentDescription: String?, // 时长
)

@Serializable
data class RecommendConfig(
    val column: Int,
    @SerialName("autoplay_card") val autoplayCard: Int,
    @SerialName("feed_clean_abtest") val feedCleanAbtest: Int,
    @SerialName("home_transfer_abtest") val homeTransferAbtest: Int,
    @SerialName("auto_refresh_time") val autoRefreshTime: Int,
    @SerialName("show_inline_danmaku") val showInlineDanmaku: Int,
)

fun RecommendResponse.toDomain(): RecommendResponseDomain {
    return RecommendResponseDomain(
        code = code,
        message = message,
        tt1 = tt1,
        data = data.toDomain()
    )
}

fun RecommendData.toDomain(): RecommendDataDomain {
    return RecommendDataDomain(
        config = config?.toDomain(),
        items = items.map { it.toDomain() }
    )
}

fun RecommendItem.toDomain(): RecommendItemDomain {
    return RecommendItemDomain(
        idx = idx,
        cover = cover,
        title = title,
        uri = uri,
        coverLeft1ContentDescription = coverLeft1ContentDescription ?: "未知观看",
        coverLeft2ContentDescription = coverLeft2ContentDescription ?: "",
        coverRightContentDescription = coverRightContentDescription ?: "00:00"
    )
}

fun RecommendConfig.toDomain(): RecommendConfigDomain {
    return RecommendConfigDomain(
        column = column,
        autoplayCard = autoplayCard,
        feedCleanAbtest = feedCleanAbtest,
        homeTransferAbtest = homeTransferAbtest,
        autoRefreshTime = autoRefreshTime,
        showInlineDanmaku = showInlineDanmaku
    )
}