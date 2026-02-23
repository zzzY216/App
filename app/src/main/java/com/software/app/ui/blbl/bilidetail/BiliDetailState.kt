package com.software.app.ui.blbl.bilidetail

data class BiliDetailState(
    val state: State = State.Idle,
)

sealed class State {
    object Idle : State()
    object Loading : State()
    object Success : State()
    class Error(val message: String) : State()
}