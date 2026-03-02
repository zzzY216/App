package com.software.app.ui.blbl.biliprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun BiliProfileScreen(
    viewModel: BiliProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        BiliProfileAvatar(
            uiState = uiState.value
        )
        BiliProfileSocialStatusRow()
        BiliProfileVIPRow()
    }
}

@Composable
fun BiliProfileVIPRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xf7c8d8))
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xef9fba))
            )
            Text(
                text = "大",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(5f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "成为大会员")
            Text(text = "了解更多权益")
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "立即开通")
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun BiliProfileSocialStatusRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        BiliProfileSocialStatusItem(
            modifier = modifier,
            text = "动态",
            value = "-"
        )
        VerticalDivider()
        BiliProfileSocialStatusItem(
            modifier = modifier,
            text = "关注",
            value = "-"
        )
        VerticalDivider()
        BiliProfileSocialStatusItem(
            modifier = modifier,
            text = "粉丝",
            value = "-"
        )
    }
}

@Composable
fun BiliProfileSocialStatusItem(
    modifier: Modifier = Modifier,
    text: String,
    value: String
) {
    Column(
        modifier = modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = value)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun BiliProfileAvatar(
    modifier: Modifier = Modifier,
    uiState: BiliProfileState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        AsyncImage(
            model = uiState.userInfo?.face,
            contentDescription = "avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(text = uiState.userInfo?.pendant?.uname ?: "")
            Text(text = "B币：获取暂无 硬币：获取暂无")
        }
    }
}