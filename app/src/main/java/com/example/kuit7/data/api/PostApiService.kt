package com.example.kuit7.data.api

import com.example.kuit7.data.dto.PostDto
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
