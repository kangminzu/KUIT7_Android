package com.example.kuit7.ui.state

import com.example.kuit7.domain.repository.model.Post

sealed interface PostUiState {
    data object Idle : PostUiState
    data object Loading : PostUiState
    data class Success(
        val message: String,
        val posts: List<Post> = emptyList()
    ) : PostUiState

    data class SingleSuccess(
        val message: String,
        val post: Post
    ) : PostUiState

    data class Error(val message: String) : PostUiState
}
