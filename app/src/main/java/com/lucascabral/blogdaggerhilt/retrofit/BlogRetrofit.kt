package com.lucascabral.blogdaggerhilt.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getBlogList(): List<BlogNetworkEntity>
}