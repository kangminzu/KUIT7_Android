package com.example.kuit7.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kuit7.ui.state.PostUiState
import com.example.kuit7.ui.viewmodel.PostViewModel

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { viewModel.fetchPosts() }
        ) {
            Text("조회")
        }

        when (val state = uiState) {
            PostUiState.Idle -> {
                Text(text = "버튼을 눌러주세요")
            }

            PostUiState.Loading -> {
                Text(text = "로딩중입니다")
            }

            is PostUiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.posts) { post ->
                        Text(text = "${post.id}. ${post.title}")
                        Text(text = post.body)
                        HorizontalDivider()
                    }
                }
            }

            is PostUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}
