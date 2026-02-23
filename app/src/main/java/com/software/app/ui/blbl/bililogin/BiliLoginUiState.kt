package com.software.app.ui.blbl.bililogin

import android.graphics.Bitmap
import com.software.app.domain.model.bili.QrCodeDataDomain
import com.software.app.domain.model.bili.QrPollDataDomain

data class BiliLoginUiState(
    val qrCodeData: QrCodeDataDomain? = null,
    val qrPollData: QrPollDataDomain? = null,

    val qrBitmap: Bitmap? = null,

    val currentUsername: String = "",
    val currentPassword: String = "",
    val currentPasswordError: String? = null,
    val currentConfirmPassword: String = "",
    val currentConfirmPasswordError: String? = null,
)