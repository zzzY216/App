package com.software.app.domain.model.bili

data class UserInfoDomain(
    val isLogin: Boolean,
    val face: String,
    val pendant: UserInfoPendantDomain
)

data class UserInfoPendantDomain(
    val uname: String,
)