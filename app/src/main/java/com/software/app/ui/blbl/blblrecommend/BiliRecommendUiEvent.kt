package com.software.app.ui.blbl.blblrecommend

sealed class BiliRecommendUiEvent {
    data class RecommendList(val idx: Long) : BiliRecommendUiEvent()
}