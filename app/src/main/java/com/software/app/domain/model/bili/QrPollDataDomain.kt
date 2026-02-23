package com.software.app.domain.model.bili

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class QrPollDataDomain(
    val code : Int,
    val message : String,
    val ttl: Int,
    val data: QrPollDataDataDomain
)
data class QrPollDataDataDomain(
    val url: String?,
    val refreshToken: String?,
    val timestamp: Long,
    val code: Int,
    val message: String
)