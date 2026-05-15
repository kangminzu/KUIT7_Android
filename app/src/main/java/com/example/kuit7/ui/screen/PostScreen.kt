package com.example.kuit7.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kuit7.domain.repository.model.Post
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
        Text(
            text = "JSONPlaceholder Posts",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.fetchPosts() }
            ) {
                Text("전체 조회")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.fetchPosts(userId = 1) }
            ) {
                Text("userId=1")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.fetchPost(id = 1) }
            ) {
                Text("단건 조회")
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.createPost() }
            ) {
                Text("등록")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.updatePost(id = 1) }
            ) {
                Text("수정")
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.deletePost(id = 1) }
            ) {
                Text("삭제")
            }
        }

        when (val state = uiState) {
            PostUiState.Idle -> {
                Text(text = "실행할 API 버튼을 눌러주세요")
            }

            PostUiState.Loading -> {
                Text(text = "API 호출 중입니다")
            }

            is PostUiState.Success -> {
                Text(text = state.message)
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.posts) { post ->
                        PostItem(post = post)
                    }
                }
            }

            is PostUiState.SingleSuccess -> {
                Text(text = state.message)
                PostItem(post = state.post)
            }

            is PostUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}

@Composable
private fun PostItem(post: Post) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "${post.id}. ${post.title}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(text = "userId: ${post.userId}")
        Text(text = post.body)
        HorizontalDivider()
    }
}
