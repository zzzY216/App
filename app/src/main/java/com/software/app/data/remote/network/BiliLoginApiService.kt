package com.software.app.data.remote.network

import com.software.app.data.remote.model.blbl.BiliResponse
import com.software.app.data.remote.model.blbl.QrCodeData
import com.software.app.data.remote.model.blbl.QrPollData
import com.software.app.data.remote.model.blbl.RecommendData
import retrofit2.http.GET
import retrofit2.http.Query

interface BiliLoginApiService {
    // 1. 生成扫码二维码
    @GET("/x/passport-login/web/qrcode/generate")
    suspend fun getQrCodeInfo(): BiliResponse<QrCodeData>

    // 2. 轮询扫码状态
    @GET("/x/passport-login/web/qrcode/poll")
    suspend fun pollQrCodeStatus(
        @Query("qrcode_key") qrcodeKey: String
    ): QrPollData
}