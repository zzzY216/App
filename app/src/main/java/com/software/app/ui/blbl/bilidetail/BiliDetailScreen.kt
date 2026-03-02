package com.software.app.ui.blbl.bilidetail

import android.transition.CircularPropagation
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.software.app.domain.model.bili.VideoDetailDomain

@Composable
fun BiliDetailScreen(
    avid: String,
    cid: String,
    qn: Int,
    type: String,
    platform: String,
    viewModel: BiliDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.onEvent(BiliDetailEvent.LoadData(avid, cid, qn, type, platform))
        viewModel.onEvent(BiliDetailEvent.GetVideoDetail(avid, cid))
    }
    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is BiliDetailUiEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 12.dp)
    ) {
        when (uiState.value.state) {
            is State.Error -> {
                Column() {
                    Text(text = "Error")
                }
            }

            State.Idle -> {
                Column() {
                    Text(text = "Idle")
                }
            }

            State.Loading -> {
                BiliLoading()
            }

            State.Success -> {
                BiliDetailSuccessContent(
                    player = viewModel.player,
                    selectedTab = selectedTab,
                    onTabClick = {
                        selectedTab = it
                    },
                    onClick = {
                        viewModel.onEvent(BiliDetailEvent.GetVideoDetail(avid, cid))
                    },
                    videoDetail = uiState.value.videoDetail
                )
            }
        }
    }
}


@Composable
fun BiliDetailSuccessContent(
    player: ExoPlayer,
    selectedTab: Int = 0,
    onTabClick: (Int) -> Unit = {},
    onClick: () -> Unit,
    videoDetail: VideoDetailDomain?,
) {
    BiliPlayer(player = player, modifier = Modifier)
    BiliVideoTabHeader(
        selectedTab = selectedTab,
        onTabClick = onTabClick
    )
    BiliDetailSuccessItem(
        onClick = onClick,
        videoDetail = videoDetail
    )
}

@Composable
fun BiliDetailSuccessItem(
    videoDetail: VideoDetailDomain?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BiliPlayerItem(videoDetail = videoDetail)
        BiliPlayerStat(videoDetail = videoDetail)
    }
}

@Composable
fun BiliDetailTitle() {

}

@Composable
fun BiliPlayerStat(
    videoDetail: VideoDetailDomain?,
) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
    ) {
        BiliPlayerStatItem(
            text = videoDetail?.stat?.like.toString(),
            {},
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        BiliPlayerStatItem(
            text = videoDetail?.stat?.like.toString(),
            {},
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        BiliPlayerStatItem(
            text = videoDetail?.stat?.like.toString(),
            {},
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        BiliPlayerStatItem(
            text = videoDetail?.stat?.like.toString(),
            {},
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        BiliPlayerStatItem(
            text = videoDetail?.stat?.like.toString(),
            {},
            onClick = {},
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun BiliPlayerStatItem(
    text: String,
    icon: @Composable () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(32.dp)
        ) {
            icon()
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = text, fontSize = 8.sp)
    }
}

@Composable
fun BiliPlayerItem(
    videoDetail: VideoDetailDomain?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = videoDetail?.owner?.face,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = videoDetail?.owner?.name ?: "获取失败",
                fontSize = 12.sp,
                color = Color(255, 102, 152)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(255, 102, 152))
                .padding(4.dp),
            contentAlignment = Alignment.Center,

            ) {
            Text(text = "+关注", fontSize = 12.sp)
        }
    }
}

@Composable
fun BiliVideoTabHeader(
    modifier: Modifier = Modifier,
    selectedTab: Int = 0,
    onTabClick: (Int) -> Unit = {}
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TabItem(
                text = "简介",
                isSelected = selectedTab == 0,
                onClick = {
                    onTabClick(0)
                },
                extend = {
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            TabItem(
                text = "评论",
                isSelected = selectedTab == 1,
                onClick = {
                    onTabClick(1)
                },
                extend = {
                    Text(
                        text = "123", color = Color.Gray, fontSize = 12.sp,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "游园会",
                color = Color(0xFF2196F3),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFFF4F4F4))
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "点我发弹幕",
                color = Color(0xFF999999),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            // 这里用自带的图标模拟图片中的弹幕图标
            Icon(
                imageVector = Icons.Default.AddTask,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    extend: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = if (isSelected) Color(0xFFFF6699) else Color.Gray,
                fontSize = 15.sp
            )
            extend()
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .width(20.dp)
                    .height(2.dp)
                    .background(Color(0xFFFF6699), CircleShape)
            )
        } else {
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun BiliLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "加载中...")
        Spacer(modifier = Modifier.height(12.dp))
        CircularPropagation()
    }
}

@Composable
fun BiliPlayer(
    player: ExoPlayer,
    modifier: Modifier = Modifier
) {
    DisposableEffect(Unit) {
        onDispose {
            player.pause()
        }
    }
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
                useController = true
            }
        },
        modifier = modifier
            .fillMaxWidth()

            .aspectRatio(16 / 9f)

    )
}