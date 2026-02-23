package com.software.app.ui.blbl.bilidetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.software.app.domain.usecase.BiliGetVideoPlayUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiliDetailViewModel @Inject constructor(
    private val biliGetVideoPlayUrlUseCase: BiliGetVideoPlayUrlUseCase,
    val player: ExoPlayer
) : ViewModel() {
    private val _uiState = MutableStateFlow(BiliDetailState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: BiliDetailEvent) {
        when (event) {
            is BiliDetailEvent.LoadData -> loadData(
                event.avid,
                event.cid,
                event.qn,
                event.type,
                event.platform
            )
        }
    }

    private fun loadData(
        avid: String,
        cid: String,
        qn: Int,
        type: String,
        platform: String
    ) {
        _uiState.update {
            it.copy(state = State.Loading)
        }
        viewModelScope.launch {
            biliGetVideoPlayUrlUseCase.invoke(avid, cid, qn, type, platform).fold(
                onSuccess = { videoData ->
                    val videoUrl = videoData.durl[0].url
                    val mediaItem = MediaItem.fromUri(videoUrl)
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.playWhenReady = true // 准备之后自动播放
                    _uiState.update {
                        it.copy(
                            state = State.Success
                        )
                    }
                },
                onFailure = { errorMessage ->
                    _uiState.update {
                        it.copy(
                            state = State.Error(errorMessage.toString())
                        )
                    }
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}