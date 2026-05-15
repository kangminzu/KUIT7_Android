package com.example.kuit7.domain.repository

import com.example.kuit7.domain.repository.model.Post

interface PostRepository {
    suspend fun getPosts(userId: Int? = null): List<Post>
    suspend fun getPost(id: Int): Post
    suspend fun createPost(title: String, body: String, userId: Int): Post
    suspend fun updatePost(id: Int, title: String, body: String, userId: Int): Post
    suspend fun deletePost(id: Int)
}
