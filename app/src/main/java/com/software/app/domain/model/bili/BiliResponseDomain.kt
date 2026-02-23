package com.software.app.domain.model.bili

data class BiliResponseDomain<T>(
    val code: Int,
    val message: String,
    val data: T?
)