package com.software.app.ui.blbl.bilidetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.software.app.domain.usecase.BiliGetVideoDetailUseCase
import com.software.app.domain.usecase.BiliGetVideoPlayUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiliDetailViewModel @Inject constructor(
    private val biliGetVideoPlayUrlUseCase: BiliGetVideoPlayUrlUseCase,
    val player: ExoPlayer,
    private val biliGetVideoDetailUseCase: BiliGetVideoDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BiliDetailState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<BiliDetailUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()


    fun onEvent(event: BiliDetailEvent) {
        when (event) {
            is BiliDetailEvent.LoadData -> loadData(
                event.avid,
                event.cid,
                event.qn,
                event.type,
                event.platform
            )

            is BiliDetailEvent.GetVideoDetail -> getVideoDetail(event.aid, event.bvid)
        }
    }

    private fun getVideoDetail(aid: String, bvid: String) {
        viewModelScope.launch {
            biliGetVideoDetailUseCase.invoke(aid, null).fold(
                onSuccess = { videoDetail ->
                    _uiEffect.send(BiliDetailUiEffect.ShowToast("成功"))
                    _uiState.update {
                        it.copy(
                            state = State.Success,
                            videoDetail = videoDetail
                        )
                    }
                },
                onFailure = { errorMessage ->
                    Log.e("BiliDetailViewModel", errorMessage.toString())
                    _uiEffect.send(BiliDetailUiEffect.ShowToast(errorMessage.toString()))
                }
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
                    Log.e("BiliDetailViewModel", errorMessage.toString())
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