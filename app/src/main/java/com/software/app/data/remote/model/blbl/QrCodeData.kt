package com.software.app.data.remote.model.blbl

import com.software.app.domain.model.bili.QrCodeDataDomain
import kotlinx.serialization.Serializable

@Serializable
data class QrCodeData(
    val url: String,
    val qrcode_key: String
)


fun QrCodeData.toDomain(): QrCodeDataDomain {
    return QrCodeDataDomain(
        url = url,
        qrcode_key = qrcode_key
    )
}