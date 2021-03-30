package com.lucascabral.blogdaggerhilt.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lucascabral.blogdaggerhilt.model.Blog
import com.lucascabral.blogdaggerhilt.retrofit.BlogNetworkEntity
import com.lucascabral.blogdaggerhilt.retrofit.BlogRetrofit
import com.lucascabral.blogdaggerhilt.retrofit.NetworkMapper
import com.lucascabral.blogdaggerhilt.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<BlogNetworkEntity, Blog>{
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): BlogRetrofit {
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)
    }
}