package com.software.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.software.app.R

@Composable
fun MainHomeScreen(
    onNavigateToFit: () -> Unit,
    onNavigateToBili: () -> Unit,
    onNavigateToLesson: () -> Unit
) {
    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                MainButtonCardItem(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_fit,
                    title = "Fit",
                    onNavigateToFit = onNavigateToFit
                )
                MainButtonCardItem(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_fit,
                    title = "Fit",
                    onNavigateToFit = onNavigateToFit
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                MainButtonCardItem(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_bili,
                    title = "Bili",
                    onNavigateToFit = onNavigateToBili
                )
                MainButtonCardItem(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_bili,
                    title = "Lesson",
                    onNavigateToFit = onNavigateToLesson
                )
            }
        }
    }
}

@Composable
fun MainButtonCardItem(
    modifier: Modifier,
    icon: Int,
    title: String,
    onNavigateToFit: () -> Unit
) {
    OutlinedCard(
        modifier = modifier.height(100.dp),
        onClick = onNavigateToFit
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title
            )
            Text(text = title)
        }
    }
}