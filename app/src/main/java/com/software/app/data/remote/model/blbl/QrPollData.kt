package com.software.app.data.remote.model.blbl

import com.software.app.domain.model.bili.QrPollDataDataDomain
import com.software.app.domain.model.bili.QrPollDataDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QrPollData(
    val code: Int,
    val message: String,
    val ttl: Int,
    val data: QrPollDataData
)
@Serializable
data class QrPollDataData(
    val url: String?,
    @SerialName("refresh_token") val refreshToken: String?,
    val timestamp: Long,
    val code: Int,
    val message: String
)

fun QrPollData.toDomain(): QrPollDataDomain {
    return QrPollDataDomain(
        code = code,
        message = message,
        ttl = ttl,
        data = data.toDomain()
    )
}

fun QrPollDataData.toDomain(): QrPollDataDataDomain {
    return QrPollDataDataDomain(
        url = url,
        refreshToken = refreshToken,
        timestamp = timestamp,
        code = code,
        message = message
    )
}