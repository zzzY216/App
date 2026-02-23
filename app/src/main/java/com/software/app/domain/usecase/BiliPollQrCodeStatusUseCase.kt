package com.software.app.domain.usecase

import com.software.app.data.repository.blbl.BlBlPollQrCodeStatusRepository
import com.software.app.domain.model.bili.QrPollDataDomain
import javax.inject.Inject

class BiliPollQrCodeStatusUseCase @Inject constructor(
    private val biliPollQrCodeStatusRepository: BlBlPollQrCodeStatusRepository
) {
    suspend operator fun invoke(qrcodeKey: String): Result<QrPollDataDomain> {
        return biliPollQrCodeStatusRepository.pollQrCodeStatus(qrcodeKey)
    }
}