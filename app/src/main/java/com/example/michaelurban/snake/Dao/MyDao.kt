package com.example.michaelurban.snake.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.example.michaelurban.snake.OwnClass.User

@Dao
interface MyDao {

    @get:Query("SELECT * FROM USERS ORDER BY points DESC")
    val users: Array<User>

    @Insert
    fun insertProduct(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT COUNT(*) FROM USERS WHERE nick = :nick")
    fun nickCount(nick: String): Int
}
