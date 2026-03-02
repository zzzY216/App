package com.software.app.ui.blbl.bilidetail

import com.software.app.domain.model.bili.VideoDetailDomain

data class BiliDetailState(
    val state: State = State.Idle,
    val videoDetail: VideoDetailDomain? = null
)

sealed class State {
    object Idle : State()
    object Loading : State()
    object Success : State()
    class Error(val message: String) : State()
}