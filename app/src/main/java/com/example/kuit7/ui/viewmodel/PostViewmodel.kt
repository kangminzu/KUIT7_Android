package com.example.kuit7.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuit7.domain.repository.PostRepository
import com.example.kuit7.ui.state.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Idle)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    fun fetchPosts(userId: Int? = null) {
        _uiState.value = PostUiState.Loading

        viewModelScope.launch {
            try {
                val posts = postRepository.getPosts(userId)
                val message = if (userId == null) {
                    "전체 게시글 ${posts.size}건 조회 성공"
                } else {
                    "userId=$userId 게시글 ${posts.size}건 조회 성공"
                }
                _uiState.value = PostUiState.Success(
                    message = message,
                    posts = posts
                )
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun fetchPost(id: Int) {
        _uiState.value = PostUiState.Loading

        viewModelScope.launch {
            try {
                val post = postRepository.getPost(id)
                _uiState.value = PostUiState.SingleSuccess(
                    message = "게시글 $id 조회 성공",
                    post = post
                )
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun createPost() {
        _uiState.value = PostUiState.Loading

        viewModelScope.launch {
            try {
                val post = postRepository.createPost(
                    title = "오늘의 회고",
                    body = "오늘 KUIT 7주차 세미나에서 Retrofit2를 배웠다.",
                    userId = 1
                )
                _uiState.value = PostUiState.SingleSuccess(
                    message = "게시글 등록 성공",
                    post = post
                )
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun updatePost(id: Int) {
        _uiState.value = PostUiState.Loading

        viewModelScope.launch {
            try {
                val post = postRepository.updatePost(
                    id = id,
                    title = "수정된 제목",
                    body = "PATCH 요청으로 수정한 본문입니다.",
                    userId = 1
                )
                _uiState.value = PostUiState.SingleSuccess(
                    message = "게시글 $id 수정 성공",
                    post = post
                )
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun deletePost(id: Int) {
        _uiState.value = PostUiState.Loading

        viewModelScope.launch {
            try {
                postRepository.deletePost(id)
                _uiState.value = PostUiState.Success(
                    message = "게시글 $id 삭제 성공"
                )
            } catch (e: Exception) {
                _uiState.value = PostUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
