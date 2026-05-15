package com.example.kuit7.data.repositoryimpl

import com.example.kuit7.data.api.PostApiService
import com.example.kuit7.data.dto.toDomain
import com.example.kuit7.domain.repository.PostRepository
import com.example.kuit7.domain.repository.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostApiService
) : PostRepository {
    override suspend fun getPost(): List<Post> {
        val dto = postService.getPosts()
        return dto.map { it.toDomain() }
    }
}
