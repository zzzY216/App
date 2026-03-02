package com.software.app.ui.lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.software.app.data.local.room.entity.Lesson

@Composable
fun LessonUiScreen(
    viewModel: LessonUiViewModel = hiltViewModel()
) {
    val lessons by viewModel.lessons.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        LessonUiContent(lessons)
    }
}

@Composable
fun LessonUiContent(
    lessons: List<Lesson>
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        LessonColumnItem()
        VerticalDivider(modifier = Modifier.fillMaxHeight(  ))
    }
}

@Composable
fun LessonColumnItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        LessonCardItem(
            text = "1",
            onClick = {}
        )
        LessonCardItem(
            text = "2",
            onClick = {}
        )
        LessonCardItem(
            text = "3",
            onClick = {}
        )
        LessonCardItem(
            text = "4",
            onClick = {}
        )
        LessonCardItem(
            text = "5",
            onClick = {}
        )
        LessonCardItem(
            text = "6",
            onClick = {}
        )
        LessonCardItem(
            text = "7",
            onClick = {}
        )
    }
}

@Composable
fun LessonCardItem(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text)
        }
    }
}