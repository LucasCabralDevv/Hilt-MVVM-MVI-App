package com.lucascabral.blogdaggerhilt.di

import android.content.Context
import androidx.room.Room
import com.lucascabral.blogdaggerhilt.model.Blog
import com.lucascabral.blogdaggerhilt.room.BlogCacheEntity
import com.lucascabral.blogdaggerhilt.room.BlogDAO
import com.lucascabral.blogdaggerhilt.room.BlogDatabase
import com.lucascabral.blogdaggerhilt.room.CacheMapper
import com.lucascabral.blogdaggerhilt.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<BlogCacheEntity, Blog> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDAO {
        return blogDatabase.blogDAO()
    }
}