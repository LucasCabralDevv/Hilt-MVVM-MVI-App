package com.lucascabral.blogdaggerhilt.repository

import com.lucascabral.blogdaggerhilt.model.Blog
import com.lucascabral.blogdaggerhilt.retrofit.BlogRetrofit
import com.lucascabral.blogdaggerhilt.retrofit.NetworkMapper
import com.lucascabral.blogdaggerhilt.room.BlogDAO
import com.lucascabral.blogdaggerhilt.room.CacheMapper
import com.lucascabral.blogdaggerhilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDAO: BlogDAO,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.getBlogList()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDAO.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDAO.getBlogList()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}