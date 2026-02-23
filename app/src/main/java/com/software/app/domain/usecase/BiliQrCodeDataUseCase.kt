package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BlBlQrCodeDataRepository
import com.software.app.domain.model.bili.QrCodeDataDomain
import javax.inject.Inject

class BiliQrCodeDataUseCase @Inject constructor(
    private val blBlQrCodeDataRepository: BlBlQrCodeDataRepository
) {
    suspend operator fun invoke(): Result<QrCodeDataDomain> {
        return blBlQrCodeDataRepository.getQrCodeData()
    }
}