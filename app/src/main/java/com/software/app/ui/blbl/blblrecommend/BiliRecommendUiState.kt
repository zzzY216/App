package com.software.app.ui.blbl.blblrecommend

import com.software.app.domain.model.bili.RecommendDataDomain

data class BiliRecommendUiState(
    val recommendVideoList: RecommendDataDomain? = null,
    val isLoading: Boolean = false
)