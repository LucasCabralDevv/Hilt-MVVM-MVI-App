package com.lucascabral.blogdaggerhilt.di

import com.lucascabral.blogdaggerhilt.repository.MainRepository
import com.lucascabral.blogdaggerhilt.retrofit.BlogRetrofit
import com.lucascabral.blogdaggerhilt.retrofit.NetworkMapper
import com.lucascabral.blogdaggerhilt.room.BlogDAO
import com.lucascabral.blogdaggerhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDAO: BlogDAO,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDAO, blogRetrofit, cacheMapper, networkMapper)
    }
}