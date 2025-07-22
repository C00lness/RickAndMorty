package com.example.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.DetailsDao
import com.example.data.entities.db.DbDetails

@Database(entities = [DbDetails::class], version = 1)
abstract class DetailsDataBase: RoomDatabase(){
    abstract fun detailsDao(): DetailsDao
    companion object {
        @Volatile
        private var INSTANCE: DetailsDataBase? = null

        fun getInstance(context: Context): DetailsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DetailsDataBase::class.java,
                    "DetailsDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}