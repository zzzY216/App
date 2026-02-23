package com.software.app.data.remote.model.blbl

import com.software.app.domain.model.bili.BiliResponseDomain

data class BiliResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)