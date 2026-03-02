package com.software.app.data.remote.model.blbl

import com.software.app.domain.model.bili.UserInfoDomain
import com.software.app.domain.model.bili.UserInfoPendantDomain
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val isLogin: Boolean,
    val face: String,
    val pendant: UserInfoPendant
)
@Serializable
data class UserInfoPendant(
    val uname: String,
)
fun UserInfo.toDomain(): UserInfoDomain {
    return UserInfoDomain(
        isLogin = isLogin,
        face = face,
        pendant = pendant.toDomain()
    )
}
fun UserInfoPendant.toDomain(): UserInfoPendantDomain {
    return UserInfoPendantDomain(
        uname = uname
    )
}