package com.lucascabral.blogdaggerhilt.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun getBlogList(): List<BlogCacheEntity>
}