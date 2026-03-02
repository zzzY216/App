package com.software.app.ui.blbl.bilihome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BiliHomeUiScreen() {
    BiliHomeUiContent()
}

@Composable
fun BiliHomeUiContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .statusBarsPadding()
    ) {
        item {
            BiliHomeTab()
        }
        stickyHeader {
            BiliHomeSnackbar()
        }
        items(50) {
            Text(
                text = "这里是内容", modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun BiliHomeSnackbar() {
    val tabs = listOf("直播", "推荐", "热门", "动画", "影视", "运动", "科技", "美食")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        PrimaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .widthIn(min = 10.dp),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }
    }
}

@Composable
fun BiliHomeTab() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(42.dp)
    ) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            color = Color.Transparent,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Row() {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Home Home")
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.Mail, contentDescription = "Mail")
    }
}