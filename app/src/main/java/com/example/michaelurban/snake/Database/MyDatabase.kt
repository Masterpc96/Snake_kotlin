package com.example.michaelurban.snake.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.example.michaelurban.snake.Dao.MyDao
import com.example.michaelurban.snake.OwnClass.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDatabase : RoomDatabase() {

    // jak jest tworzony ten getter
    abstract val dao: MyDao

    companion object {
        private val DB_NAME = "usersDataBase.db"
        private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = create(context)
            }
            return instance as MyDatabase
        }

        private fun create(context: Context): MyDatabase {
            return Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    DB_NAME).allowMainThreadQueries().build()
        }
    }
}
