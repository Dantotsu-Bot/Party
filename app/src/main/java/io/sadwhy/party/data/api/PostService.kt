package io.sadwhy.party.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import io.sadwhy.party.data.model.Recent
import io.sadwhy.party.data.model.Post

interface PostService {
    @GET("v1/posts")
    suspend fun getRecentPosts(): Response<Recent>

    @GET("v1/{service}/user/{creator_id}/post/{post_id}")
    suspend fun getPost(
        @Path("service") service: String,
        @Path("creator_id") creatorId: String,
        @Path("post_id") postId: String
    ): Response<Post>
}