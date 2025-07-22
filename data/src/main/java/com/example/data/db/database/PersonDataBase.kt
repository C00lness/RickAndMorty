package com.example.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.PersonDao
import com.example.data.entities.db.DbPerson

@Database(entities = [DbPerson::class], version = 3, exportSchema = false)
abstract class PersonDataBase: RoomDatabase(){
    abstract fun personDao(): PersonDao
    companion object {
        @Volatile
        private var INSTANCE: PersonDataBase? = null

        fun getInstance(context: Context): PersonDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDataBase::class.java,
                    "PersonDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}