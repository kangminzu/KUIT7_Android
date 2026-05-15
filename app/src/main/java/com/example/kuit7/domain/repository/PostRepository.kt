package com.example.kuit7.domain.repository

import com.example.kuit7.domain.repository.model.Post

interface PostRepository {
    suspend fun getPost(): List<Post>
}
