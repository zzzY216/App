package com.software.app.ui.blbl.bilidetail

sealed class BiliDetailEvent {
    class LoadData(
        val avid: String,
        val cid: String,
        val qn: Int,
        val type: String,
        val platform: String
    ) : BiliDetailEvent()
    class GetVideoDetail(val aid: String, val bvid: String): BiliDetailEvent()
}

sealed class BiliDetailUiEffect {
    data class ShowToast(val message: String) : BiliDetailUiEffect()
}