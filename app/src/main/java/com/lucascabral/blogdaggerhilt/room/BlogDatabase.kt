package com.lucascabral.blogdaggerhilt.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDAO(): BlogDAO

    companion object {
        val DATABASE_NAME = "blog_db"
    }
}