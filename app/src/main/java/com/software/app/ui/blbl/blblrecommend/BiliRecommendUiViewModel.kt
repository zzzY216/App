package com.software.app.ui.blbl.blblrecommend

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.software.app.domain.usecase.BiliGetVideoPlayUrlUseCase
import com.software.app.domain.usecase.BiliRecommendVideoUseCase
import com.software.app.domain.usecase.GetRecommendVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiliRecommendUiViewModel @Inject constructor(
    private val biliRecommendVideoUseCase: BiliRecommendVideoUseCase,
    private val getRecommendVideosUseCase: GetRecommendVideosUseCase,
    private val biliGetVideoPlayUrlUseCase: BiliGetVideoPlayUrlUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BiliRecommendUiState())
    val uiState = _uiState.asStateFlow()

    val videoPagingFlow = getRecommendVideosUseCase()
        .cachedIn(viewModelScope)

    fun onEvent(event: BiliRecommendUiEvent) {
        when (event) {
            is BiliRecommendUiEvent.RecommendList -> recommendList(event.idx)
        }
    }

    private fun recommendList(idx: Long) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val response = biliRecommendVideoUseCase.invoke(idx, false, 1, 0)
            Log.d("BiliHomeUiViewModel", "response: $response")
            response.fold(
                onSuccess = { recommendDataDomain ->
                    _uiState.update {
                        it.copy(
                            recommendVideoList = recommendDataDomain,
                            isLoading = false
                        )
                    }
                },
                onFailure = {}
            )
        }
    }
}