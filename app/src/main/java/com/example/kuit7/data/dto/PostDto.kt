package com.example.kuit7.data.dto

import com.example.kuit7.domain.repository.model.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

@Serializable
data class PostRequestDto(
    val title: String,
    val body: String,
    val userId: Int
)

fun PostDto.toDomain(): Post = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)
