package com.example.kuit7.data.repositoryimpl

import com.example.kuit7.data.api.PostApiService
import com.example.kuit7.data.dto.PostRequestDto
import com.example.kuit7.data.dto.toDomain
import com.example.kuit7.domain.repository.PostRepository
import com.example.kuit7.domain.repository.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostApiService
) : PostRepository {
    override suspend fun getPosts(userId: Int?): List<Post> {
        val dto = postService.getPosts(userId)
        return dto.map { it.toDomain() }
    }

    override suspend fun getPost(id: Int): Post =
        postService.getPost(id).toDomain()

    override suspend fun createPost(title: String, body: String, userId: Int): Post =
        postService.createPost(
            PostRequestDto(
                title = title,
                body = body,
                userId = userId
            )
        ).toDomain()

    override suspend fun updatePost(id: Int, title: String, body: String, userId: Int): Post =
        postService.updatePost(
            id = id,
            request = PostRequestDto(
                title = title,
                body = body,
                userId = userId
            )
        ).toDomain()

    override suspend fun deletePost(id: Int) {
        val response = postService.deletePost(id)
        if (!response.isSuccessful) {
            error("삭제 실패: HTTP ${response.code()}")
        }
    }
}
