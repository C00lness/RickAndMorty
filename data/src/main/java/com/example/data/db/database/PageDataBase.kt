package com.example.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.PageDao
import com.example.data.entities.db.DbPage

@Database(entities = [DbPage::class], version = 1)
abstract class PageDataBase: RoomDatabase(){
    abstract fun pageDao(): PageDao
    companion object {
        @Volatile
        private var INSTANCE: PageDataBase? = null

        fun getInstance(context: Context): PageDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageDataBase::class.java,
                    "PageDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}